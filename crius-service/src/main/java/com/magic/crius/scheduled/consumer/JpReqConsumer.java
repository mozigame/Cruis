package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.assemble.PrizeDetailAssemService;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.service.JpReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.JpReq;
import com.magic.crius.vo.ReqQueryVo;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.CriusInitConstants.POLL_TIME;
import static com.magic.crius.constants.CriusInitConstants.THREAD_SIZE;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 16:37
 */
@Component
public class JpReqConsumer {

    private static final Logger logger = Logger.getLogger(JpReqConsumer.class);

    private ExecutorService jpTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService jpHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;
    @Resource
    private JpReqService jpReqService;
    @Resource
    private PrizeDetailAssemService prizeDetailAssemService;
    
    @Resource
    private UserOrderDetailAssemService userOrderDetailAssemService;

    @Resource
    private BaseReqService baseReqService;


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            jpTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						ApiLogger.error("---detailCalculate-- jp", e);
					}
                }
            });
        }

        jpHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal jp");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					ApiLogger.error("---detailCalculate-task-- jp", e);
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
        List<JpReq> reqList = jpReqService.batchPopRedis(date);
        int queuePopCount = 0;
        while (FailedRedisQueue.jpQueue.size() > 0) {
            if (++queuePopCount > RedisConstants.BATCH_POP_NUM) {
                logger.info("currentDataCalculate jp queuePopCount > 100, process insert, list.size is : " + reqList.size());
                flushData(reqList);
            }
            reqList.add(FailedRedisQueue.jpQueue.poll());
        }
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("currentDataCalculate jp pop datas, size : " + reqList.size());
            flushData(reqList);
            reqList = jpReqService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("currentDataCalculate jp,", e);
            }
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<JpReq> list) {
        if (list != null && list.size() > 0) {
            List<PrizeDetail> prizeDetails = new ArrayList<>();
            List<UserOrderDetail> orderDetails = new ArrayList<>();
            List<JpReq> sucReqs = new ArrayList<>();
            for (JpReq req : list) {
                /*彩金明细*/
                prizeDetails.add(assemblePrizeDetail(req));
                
                orderDetails.add(userOrderDetailAssemService.assembleUserOrderDetail(req));
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            prizeDetailAssemService.batchSave(prizeDetails);

            userOrderDetailAssemService.batchSaveDetails(orderDetails);
            //todo 成功的id处理
            if (!jpReqService.saveSuc(sucReqs)) {

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
        List<JpReq> reqList = jpReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            logger.info("------repairCacheHistoryTask ,jp , list : " + reqList.size());
            flushData(reqList);
            reqList = jpReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.jpReq, Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.jpReq);
        repairLock.setValue(CriusConstants.REPAIR_LOCK_VALUE);
        if (repairLockService.save(repairLock)) {
//            mongoFailed(startDate.getTimeInMillis(), endDate.getTime());
            mongoNoProc(startDate.getTimeInMillis(), endDate.getTime(), hhDate, Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMdd)));
        }

    }

    /**
     * mongo插入失败数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoFailed(Long startTime, Long endTime) {
        List<JpReq> failedReqs = jpReqService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,jp , reqIds :"+ failedReqs.size()+" , startTime : "+ startTime+" endTime :" + endTime);
            flushData(failedReqs);
        }
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime, String hhDate, Integer pdate) {
        ReqQueryVo queryVo = new ReqQueryVo();
        queryVo.setStartTime(startTime);
        queryVo.setEndTime(endTime);
        queryVo.setPdate(pdate);
        List<Long> reqIds = jpReqService.getSucIds(queryVo);
        queryVo.setReqIds(reqIds);

        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_JP, hhDate)));


        List<JpReq> withDrawReqs = jpReqService.getNotProc(queryVo, pageable);
        while (withDrawReqs != null && withDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,jp ,  noProcSize : " + withDrawReqs.size() + " , startTime : " + startTime + " endTime :" + endTime);
            flushData(withDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_JP, hhDate)));
            withDrawReqs = jpReqService.getNotProc(queryVo, pageable);

        }
    }

    private PrizeDetail assemblePrizeDetail(JpReq req) {
        PrizeDetail detail = new PrizeDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setOrderId(req.getBillId());
        detail.setPrizeType(req.getJpType());
        //todo 待确定
        detail.setPrizeTypeName("");
        detail.setGameId(req.getGameId() + "");
        detail.setOrderCount(req.getJpAmount());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        return detail;
    }

    private JpReq assembleSucReq(JpReq req) {
        /*成功的数据*/
        JpReq sucReq = new JpReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        sucReq.setConsumerTime(req.getConsumerTime());
        sucReq.setBillId(req.getBillId());
        return sucReq;
    }
}
