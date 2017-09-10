package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.assemble.OwnerCompanyAccountDetailAssemService;
import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.service.CashbackReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.CashbackReq;
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
 * Time: 12:40
 */
@Component
public class CashbackReqConsumer {

    private static final Logger logger = Logger.getLogger(CashbackReqConsumer.class);

    private ExecutorService cashBackTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService cashBackHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private CashbackReqService cashbackReqService;
    @Resource
    private RepairLockService repairLockService;
    /*会员反水详情*/
    @Resource
    private OwnerReforwardDetailAssemService ownerReforwardDetailAssemService;
    /*公司账目汇总*/
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    /*账户交易明细*/
    @Resource
    private UserTradeAssemService userTradeAssemService;
    @Resource
    private BaseReqService baseReqService;


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            cashBackTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						ApiLogger.error("---detailCalculate-- cashback", e);
					}
                }
            });
        }

        cashBackHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal cashback");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					ApiLogger.error("---detailCalculate-task-- cashback", e);
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
        List<CashbackReq> reqList = cashbackReqService.batchPopRedis(date);
        int queuePopCount = 0;
        while (FailedRedisQueue.cashbackQueue.size() > 0) {
            if (++queuePopCount > RedisConstants.BATCH_POP_NUM) {
                logger.info("currentDataCalculate cashback queuePopCount > 100, process insert, list.size is : " + reqList.size());
                flushData(reqList);
            }
            reqList.add(FailedRedisQueue.cashbackQueue.poll());
        }
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("------currentDataCalculate ,cashback , list : " + reqList.size());
            flushData(reqList);
            reqList = cashbackReqService.batchPopRedis(date);
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
    private void flushData(Collection<CashbackReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerReforwardDetail> ownerReforwardDetailHashMap = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<CashbackReq> sucReqs = new ArrayList<>();
            for (CashbackReq req : list) {
                /*反水详情*/
                ownerReforwardDetailHashMap.add(assembleOwnerReforwardDetail(req));
                /*
                 * 如果返水金额大于0 返水有意义
                 */
                if (req.getAmount() > 0) {
                    /*公司账目汇总*/
                    ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));
                    /*账户交易明细*/
                    userTrades.add(userTradeAssemService.assembleUserTrade(req));
                }

                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }

            ownerReforwardDetailAssemService.batchSave(ownerReforwardDetailHashMap);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            userTradeAssemService.batchSave(userTrades);

            /*mongo添加处理成功的数据*/
            if (!cashbackReqService.saveSuc(sucReqs)) {
                //todo
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
        List<CashbackReq> reqList = cashbackReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            logger.info("------repairCacheHistoryTask ,cashback , list : " + reqList.size());
            flushData(reqList);
            reqList = cashbackReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.cashbackReq, Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.cashbackReq);
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
        List<CashbackReq> failedReqs = cashbackReqService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,cashback , startTime , reqIds :"+ failedReqs.size()+" : "+ startTime+" endTime :" + endTime);
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
        List<Long> reqIds = cashbackReqService.getSucIds(queryVo);
        queryVo.setReqIds(reqIds);
        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_CAHSBACK, hhDate)));


        List<CashbackReq> notProcDrawReqs = cashbackReqService.getNotProc(queryVo, pageable);
        while (notProcDrawReqs != null && notProcDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,cashback ,noProcReqIds :" + notProcDrawReqs.size() + " , startTime : " + startTime + " endTime :" + endTime);
            flushData(notProcDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_CAHSBACK, hhDate)));
            notProcDrawReqs = cashbackReqService.getNotProc(queryVo, pageable);
        }
    }


    private OwnerReforwardDetail assembleOwnerReforwardDetail(CashbackReq req) {
        OwnerReforwardDetail summmary = new OwnerReforwardDetail();
        summmary.setOwnerId(req.getOwnerId());
        summmary.setUserId(req.getUserId());
        summmary.setGameType(String.valueOf(req.getGamePlatformHalltypeId()));
        //TODO 等待获取
        summmary.setOrderNum(1);
        summmary.setOrderCount(req.getBettAmount());
        summmary.setEffectOrderCount(req.getVaildBettAmount());
        summmary.setReforwardMoneyCount(req.getAmount());
        summmary.setOrderId(req.getBillId());
        summmary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        summmary.setCreateTime(req.getProduceTime());
        summmary.setUpdateTime(req.getProduceTime());
        return summmary;
    }

    private CashbackReq assembleSucReq(CashbackReq req) {
        /*成功的数据*/
        CashbackReq sucReq = new CashbackReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        sucReq.setConsumerTime(req.getConsumerTime());
        sucReq.setBillId(req.getBillId());
        sucReq.setPdate(req.getPdate());
        return sucReq;
    }
}
