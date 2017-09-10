package com.magic.crius.scheduled.consumer;

import static com.magic.crius.constants.CriusInitConstants.POLL_TIME;
import static com.magic.crius.constants.CriusInitConstants.THREAD_SIZE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.ReqQueryVo;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerAwardDetailAssemService;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.DealerRewardReq;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 13:49
 */
@Component
public class DealerRewardReqConsumer {
    private static final Logger logger = Logger.getLogger(DealerRewardReqConsumer.class);

    private ExecutorService dealerRewardTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService dealerRewardHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private DealerRewardReqService dealerRewardReqService;
    /*打赏明细*/
    @Resource
    private OwnerAwardDetailAssemService ownerAwardDetailAssemService;
    @Resource
    private RepairLockService repairLockService;
    
    @Resource
    private UserOrderDetailAssemService userOrderDetailAssemService;
    @Resource
    private BaseReqService baseReqService;

    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            dealerRewardTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						ApiLogger.error("---detailCalculate-- dealerReward", e);
					}
                }
            });
        }

        dealerRewardHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal dealerReward");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					ApiLogger.error("---detailCalculate-task-- dealerReward", e);
				}
            }
        });
    }

    /**
     * redis中数据处理
     *
     * @param date
     */
    private void currentDataCalculate(Date date) {
        int countNum = 0;
        List<DealerRewardReq> reqList = dealerRewardReqService.batchPopRedis(date);
        int queuePopCount = 0;
        while (FailedRedisQueue.dealerRewardQueue.size() > 0) {
            if (++queuePopCount > RedisConstants.BATCH_POP_NUM) {
                logger.info("currentDataCalculate dealerReward queuePopCount > 100, process insert, list.size is : " + reqList.size());
                flushData(reqList);
            }
            reqList.add(FailedRedisQueue.dealerRewardQueue.poll());
        }
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("------currentDataCalculate ,dealerReward , list : " + reqList.size());
            flushData(reqList);
            reqList = dealerRewardReqService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("--currentDataCalculate dealerReward--",e);
            }
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<DealerRewardReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerAwardDetail> details = new ArrayList<>();
            List<DealerRewardReq> sucReqs = new ArrayList<>();
            List<UserOrderDetail> orderDetails = new ArrayList<>();
            for (DealerRewardReq req : list) {
            	
                details.add(assembleOwnerAwardDetail(req));
                
                orderDetails.add(userOrderDetailAssemService.assembleUserOrderDetail(req));
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }

            ownerAwardDetailAssemService.batchSave(details);
            
            userOrderDetailAssemService.batchSaveDetails(orderDetails);
            //todo 成功的id处理
            if (!dealerRewardReqService.saveSuc(sucReqs)) {

            }
        }
    }

    /**
     * 处理缓存中上一个小时缓存中未处理的数据
     *
     * @param date
     */
    private void repairCacheHistoryTask(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -1);
        List<DealerRewardReq> reqList = dealerRewardReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = dealerRewardReqService.batchPopRedis(calendar.getTime());
        }
    }

    /**
     * 纠正mongo中上一个小时内添加失败的数据和未处理的数据
     */
    private void repairMongoAbnormal(Date date) {
        String hhDate = DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH);
        Date endDate = DateUtil.parseDate(hhDate, DateUtil.format_yyyyMMddHH);
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(endDate);
        startDate.add(Calendar.HOUR, -1);
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.dealerRewardReq, Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.dealerRewardReq);
        repairLock.setValue(CriusConstants.REPAIR_LOCK_VALUE);
        if (repairLockService.save(repairLock)) {
//            mongoFailed(startDate.getTimeInMillis(), endDate.getTime());
            mongoNoProc(startDate.getTimeInMillis(), endDate.getTime(), hhDate);
        }

    }

    /**
     * mongo插入失败数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoFailed(Long startTime, Long endTime) {
        List<DealerRewardReq> failedReqs = dealerRewardReqService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,dealerReward , reqIds :"+ failedReqs.size()+" , startTime : "+ startTime+" endTime :" + endTime);
            flushData(failedReqs);
        }
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime, String hhDate) {
        ReqQueryVo queryVo = new ReqQueryVo();
        queryVo.setStartTime(startTime);
        queryVo.setEndTime(endTime);

        List<Long> reqIds = dealerRewardReqService.getSucIds(queryVo);
        queryVo.setReqIds(reqIds);
        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_DS, hhDate)));


        List<DealerRewardReq> withDrawReqs = dealerRewardReqService.getNotProc(queryVo, pageable);
        while (withDrawReqs != null && withDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,dealerReward , noProcSize: " + withDrawReqs.size() + " , startTime : " + startTime + " endTime :" + endTime);
            flushData(withDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_DS, hhDate)));
            withDrawReqs = dealerRewardReqService.getNotProc(queryVo, pageable);
        }
    }

    private OwnerAwardDetail assembleOwnerAwardDetail(DealerRewardReq req) {
        OwnerAwardDetail detail = new OwnerAwardDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBillId());
        //TODO 待确定
        detail.setCurrencyCode("");
        //todo 待确定
        detail.setCurrencyName("");
        //Todo 待确定 游戏桌号是局号
        detail.setOfficeNum(req.getGameDeskNum().intValue());
        detail.setOrderCount(req.getRewardAmount());
        detail.setDealerCode(String.valueOf(req.getDealerId()));
        detail.setDealerName(req.getDealerName());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        return detail;
    }

    private DealerRewardReq assembleSucReq(DealerRewardReq req) {
        /*成功的数据*/
        DealerRewardReq sucReq = new DealerRewardReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        sucReq.setConsumerTime(req.getConsumerTime());
        sucReq.setBillId(req.getBillId());
        return sucReq;
    }

}
