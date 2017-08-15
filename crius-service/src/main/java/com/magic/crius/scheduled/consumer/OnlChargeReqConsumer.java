package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.*;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.user.vo.MemberConditionVo;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;
import static com.magic.crius.constants.ScheduleConsumerConstants.THREAD_SIZE;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 14:12
 */
@Component
public class OnlChargeReqConsumer {

    private static final Logger logger = Logger.getLogger(OnlChargeReqConsumer.class);

    private ExecutorService onlChargeTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService onlChargeHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;

    @Resource
    private OnlChargeReqService onlChargeService;
    /*线上入款汇总*/
    @Resource
    private OwnerOnlineFlowDetailAssemService ownerOnlineFlowDetailAssemService;

    /*公司账目汇总*/
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    /*会员入款明细*/
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;
    @Resource
    private MemberConditionVoAssemService memberConditionVoAssemService;
    @Resource
    private UserTradeSummaryAssemService userTradeSummaryAssemService;
    @Resource
    private BaseReqService baseReqService;

    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            onlChargeTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						ApiLogger.error("---detailCalculate--onlCharge", e);
					}
                }
            });
        }

        onlChargeHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal onlCharge");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					ApiLogger.error("---detailCalculate-task--onlCharge", e);
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
        List<OnlChargeReq> reqList = onlChargeService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("currentDataCalculate onlCharge pop datas, size : " + reqList.size());
            flushData(reqList);
            reqList = onlChargeService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("currentDataCalculate onlCharge ," , e);
            }
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<OnlChargeReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerOnlineFlowDetail> ownerOnlineFlowDetails = new ArrayList<>();
            List<UserFlowMoneyDetail> userFlowMoneyDetails = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<OnlChargeReq> sucReqs = new ArrayList<>();
            Map<Long, MemberConditionVo> memberConditionVoMap = new HashMap<>();
            Map<Long, UserTradeSummary> userTradeSummaries = new HashMap<>();
            for (OnlChargeReq req : list) {
                /*线上入款汇总*/
                ownerOnlineFlowDetails.add(assembleOwnerOnlineFlowDetail(req));
                /*会员入款详情*/
                userFlowMoneyDetails.add(userFlowMoneyDetailAssemService.assembleUserFlowMoneyDetail(req));
                /*公司账目汇总*/
                ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));
                /*账户交易明细*/
                userTrades.add(userTradeAssemService.assembleUserTrade(req));


                /*会员入款*/
                if (memberConditionVoMap.get(req.getUserId()) == null) {
                    memberConditionVoMap.put(req.getUserId(), memberConditionVoAssemService.assembleDepositMVo(req));
                } else {
                    MemberConditionVo vo  = memberConditionVoMap.get(req.getUserId());
                    vo.setDepositCount(vo.getDepositCount() + 1);
                    vo.setDepositMoney(vo.getDepositMoney() + req.getChargeAmount());
                }

                    /*个人资金汇总*/
                if (userTradeSummaries.get(req.getUserId()) == null) {
                    userTradeSummaries.put(req.getUserId(), userTradeSummaryAssemService.assembleUserTradeSummary(req));
                } else {
                    UserTradeSummary summary = userTradeSummaries.get(req.getUserId());
                    summary.setTotalCount(summary.getTotalCount() + 1);
                    summary.setTotalMoney(summary.getTotalMoney() + req.getChargeAmount());
                    if (summary.getMaxMoney() < req.getChargeAmount()) {
                        summary.setMaxMoney(req.getChargeAmount());
                    }
                }

                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            ownerOnlineFlowDetailAssemService.batchSave(ownerOnlineFlowDetails);
            userFlowMoneyDetailAssemService.batchSave(userFlowMoneyDetails);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            userTradeAssemService.batchSave(userTrades);
            memberConditionVoAssemService.batchRecharge(memberConditionVoMap.values());
            userTradeSummaryAssemService.batchSave(userTradeSummaries);
            //todo 成功的id处理
            if (!onlChargeService.saveSuc(sucReqs)) {

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
        List<OnlChargeReq> reqList = onlChargeService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            logger.info("------repairCacheHistoryTask ,onlCharge , list : " + reqList.size());
            flushData(reqList);
            reqList = onlChargeService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.onlChargeReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.onlChargeReq.name());
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
        List<OnlChargeReq> failedReqs = onlChargeService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,onlCharge , reqIds :"+ failedReqs.size()+" , startTime : "+ startTime+" endTime :" + endTime);
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
        List<Long> reqIds = onlChargeService.getSucIds(startTime, endTime);
        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_ONL_CHARGE, hhDate)));
        List<OnlChargeReq> withDrawReqs = onlChargeService.getNotProc(startTime, endTime, reqIds, pageable);
        while (withDrawReqs != null && withDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,onlCharge , noProcSize : "+withDrawReqs.size()+",startTime : " + startTime + " endTime :" + endTime);
            flushData(withDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_ONL_CHARGE, hhDate)));
            withDrawReqs = onlChargeService.getNotProc(startTime, endTime, reqIds, pageable);
        }

    }

    private OwnerOnlineFlowDetail assembleOwnerOnlineFlowDetail(OnlChargeReq req) {
        OwnerOnlineFlowDetail flow = new OwnerOnlineFlowDetail();
        flow.setOwnerId(req.getOwnerId());
        flow.setOperateFlowMoneyCount(req.getChargeAmount());
        //todo
        flow.setOperateFlowNum(1);
        flow.setMerchantCode(req.getMerchantCode());
        flow.setMerchantName(req.getMerchantName());
        flow.setPaySystemCode(req.getPaySystemCode());
        flow.setPaySystemName(req.getPaySystemName());
        flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return flow;
    }



    private OnlChargeReq assembleSucReq(OnlChargeReq req) {
        /*成功的数据*/
        OnlChargeReq sucReq = new OnlChargeReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }
}
