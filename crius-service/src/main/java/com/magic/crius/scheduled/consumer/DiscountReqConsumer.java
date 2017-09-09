package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.*;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.DiscountReq;
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
 * Time: 14:07
 */
@Component
public class DiscountReqConsumer {


    private static final Logger logger = Logger.getLogger(DiscountReqConsumer.class);

    private ExecutorService discountTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService discountHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;
    @Resource
    private DiscountReqService discountReqService;
    /*业主优惠汇总*/
    @Resource
    private OwnerPreferentialDetailAssemService ownerPreferentialDetailAssemService;
    /*会员优惠汇总*/
    @Resource
    private UserPreferentialDetailAssemService userPreferentialDetailAssemService;
    /*公司账目汇总*/
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Resource
    private UserTradeSummaryAssemService userTradeSummaryAssemService;

    @Resource
    private BaseReqService baseReqService;

    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            discountTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						ApiLogger.error("---detailCalculate-- discount", e);
					}
                }
            });
        }

        discountHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal discount");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					ApiLogger.error("---detailCalculate-task-- discount", e);
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
        List<DiscountReq> reqList = discountReqService.batchPopRedis(date);
        int queuePopCount = 0;
        while (FailedRedisQueue.discountQueue.size() > 0) {
            if (++queuePopCount > RedisConstants.BATCH_POP_NUM) {
                logger.info("currentDataCalculate discount queuePopCount > 100, process insert, list.size is : " + reqList.size());
                flushData(reqList);
            }
            reqList.add(FailedRedisQueue.discountQueue.poll());
        }
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("currentDataCalculate discount pop datas, size : " + reqList.size());
            flushData(reqList);
            reqList = discountReqService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("--currentDataCalculate discount ,",e);
            }
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<DiscountReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerPreferentialDetail> ownerOnlineFlowDetailMap = new ArrayList<>();
            List<UserPreferentialDetail> userPreferentialDetailHashMap = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<DiscountReq> sucReqs = new ArrayList<>();
            Map<Long, UserTradeSummary> userTradeSummaries = new HashMap<>();
            for (DiscountReq req : list) {
                /*业主优惠汇总*/
                ownerOnlineFlowDetailMap.add(assembleOwnerPreferentialDetail(req));
                /*会员优惠汇总*/
                userPreferentialDetailHashMap.add(userPreferentialDetailAssemService.assembleUserPreferentialDetail(req));
                /*公司账目汇总*/
                ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));
                /*账户交易明细*/
                userTrades.add(userTradeAssemService.assembleUserTrade(req));
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));

                /*个人资金汇总*/
                if (userTradeSummaries.get(req.getUserId()) == null) {
                    userTradeSummaries.put(req.getUserId(), userTradeSummaryAssemService.assembleUserTradeSummary(req));
                } else {
                    UserTradeSummary summary = userTradeSummaries.get(req.getUserId());
                    summary.setTotalCount(summary.getTotalCount() + 1);
                    summary.setTotalMoney(summary.getTotalMoney() + req.getOfferAmount());
                    if (summary.getMaxMoney() < req.getOfferAmount()) {
                        summary.setMaxMoney(req.getOfferAmount());
                    }
                }

            }
            ownerPreferentialDetailAssemService.batchSave(ownerOnlineFlowDetailMap);

            userPreferentialDetailAssemService.batchSave(userPreferentialDetailHashMap);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            userTradeAssemService.batchSave(userTrades);
            userTradeSummaryAssemService.batchSave(userTradeSummaries);
            //todo 成功的id处理
            if (!discountReqService.saveSuc(sucReqs)) {

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
        List<DiscountReq> reqList = discountReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            logger.info("------repairCacheHistoryTask ,discount , list : " + reqList.size());
            flushData(reqList);
            reqList = discountReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.discountReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.cashbackReq.name());
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
        List<DiscountReq> failedReqs = discountReqService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,discount , reqIds :"+ failedReqs.size()+" , startTime : "+ startTime+" endTime :" + endTime);
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
        List<Long> reqIds = discountReqService.getSucIds(startTime, endTime);
        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_DISCOUNT, hhDate)));
        List<DiscountReq> withDrawReqs = discountReqService.getNotProc(startTime, endTime, reqIds, pageable);
        while (withDrawReqs != null && withDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,discount , noProcSize : "+withDrawReqs.size()+" startTime : " + startTime + " endTime :" + endTime);
            flushData(withDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_DISCOUNT, hhDate)));
            withDrawReqs = discountReqService.getNotProc(startTime, endTime, reqIds, pageable);
        }
    }

    private OwnerPreferentialDetail assembleOwnerPreferentialDetail(DiscountReq req) {
        OwnerPreferentialDetail ownerPreferentialDetail = new OwnerPreferentialDetail();
        ownerPreferentialDetail.setOwnerId(req.getOwnerId());
        ownerPreferentialDetail.setPreferentialMoneyCount(req.getOfferAmount());
        ownerPreferentialDetail.setPreferentialNum(1);
        ownerPreferentialDetail.setPreferentialType(req.getOfferTypeId());
        ownerPreferentialDetail.setPreferentialTypeName(req.getOfferTypeName());
        ownerPreferentialDetail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return ownerPreferentialDetail;
    }


    private DiscountReq assembleSucReq(DiscountReq req) {
        /*成功的数据*/
        DiscountReq sucReq = new DiscountReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        sucReq.setConsumerTime(req.getConsumerTime());
        return sucReq;
    }
}
