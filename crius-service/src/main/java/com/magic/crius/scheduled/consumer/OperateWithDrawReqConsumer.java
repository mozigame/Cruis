package com.magic.crius.scheduled.consumer;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;
import static com.magic.crius.constants.ScheduleConsumerConstants.THREAD_SIZE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.magic.crius.assemble.*;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.*;
import com.magic.crius.service.BaseReqService;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.util.PropertiesLoad;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.PreWithdrawReq;
import com.magic.user.vo.MemberConditionVo;

/**
 * 人工提现
 */
@Component
public class OperateWithDrawReqConsumer {

    private static final Logger logger = Logger.getLogger(OperateWithDrawReqConsumer.class);


    private ExecutorService operateWithDrawTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService operateWithDrawHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private OperateWithDrawReqService operateWithDrawReqService;
    /*人工出款详情*/
    @Resource
    private OwnerOperateOutDetailAssemService ownerOperateOutDetailAssemService;
    /*公司账目汇总*/
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;

    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Resource
    private RepairLockService repairLockService;

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
            operateWithDrawTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						logger.error("---detailCalculate--operateWithDraw", e);
					}
                }
            });
        }

        operateWithDrawHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
                    if (PropertiesLoad.repairScheduleFlag()) {
                        logger.info("----repairMongoAbnormal operateWithDraw");
                        repairMongoAbnormal(date);
                    }
				} catch (Exception e) {
					logger.error("---detailCalculate-task--operateWithDraw", e);
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
        List<OperateWithDrawReq> reqList = operateWithDrawReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("currentDataCalculate operateWithDraw pop datas, size : " + reqList.size());
            flushData(reqList);
            reqList = operateWithDrawReqService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("--currentDataCalculate--operateWithDraw, ",e);
            }
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<OperateWithDrawReq> list) {
        if (list != null && list.size() > 0) {
        	List<OwnerOperateOutDetail> ownerOperateOutDetails = new ArrayList<>();
            List<UserOutMoneyDetail> userOutMoneyDetails = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<OperateWithDrawReq> sucReqs = new ArrayList<>();
            Map<Long, MemberConditionVo> memberConditionVoMap = new HashMap<>();
            Map<Long, UserTradeSummary> userTradeSummaries = new HashMap<>();
            for (OperateWithDrawReq req : list) {
                /*人工出款详情*/
               
                /*公司账目汇总*/
                //ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));

                /*会员账号汇总*/
                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (int i = 0; i < req.getUserIds().length; i++) {
                        userTrades.add(userTradeAssemService.assembleUserTrade(req, req.getUserIds()[i], req.getBillIds()[i]));
                        ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req,req.getUserIds()[i]));
                        userOutMoneyDetails.add(userOutMoneyDetailAssemService.assembleUserOutMoneyDetail(req, req.getUserIds()[i], req.getBillIds()[i]));
                        ownerOperateOutDetails.add(assembleOwnerOperateOutDetail(req));
                        /*会员提款*/
                        if (memberConditionVoMap.get(req.getUserIds()[i]) == null) {
                            memberConditionVoMap.put(req.getUserIds()[i], memberConditionVoAssemService.assembleWithdrawMVo(req, req.getUserIds()[i]));
                        } else {
                            MemberConditionVo vo  = memberConditionVoMap.get(req.getUserIds()[i]);
                            vo.setWithdrawCount(vo.getWithdrawCount() + 1);
                            vo.setWithdrawMoney(vo.getWithdrawMoney() + req.getAmount());
                        }

                        /*个人资金汇总*/
                        if (userTradeSummaries.get(req.getUserIds()[i]) == null) {
                            userTradeSummaries.put(req.getUserIds()[i], userTradeSummaryAssemService.assembleUserTradeSummary(req, req.getUserIds()[i]));
                        } else {
                            UserTradeSummary summary = userTradeSummaries.get(req.getUserIds()[i]);
                            summary.setTotalCount(summary.getTotalCount() + 1);
                            summary.setTotalMoney(summary.getTotalMoney() + req.getAmount());
                            if (summary.getMaxMoney() < req.getAmount()) {
                                summary.setMaxMoney(req.getAmount());
                            }
                        }
                    }
                }
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            ownerOperateOutDetailAssemService.batchSave(ownerOperateOutDetails);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            memberConditionVoAssemService.batchWithdraw(memberConditionVoMap.values());
            userTradeAssemService.batchSave(userTrades);
            userOutMoneyDetailAssemService.batchSave(userOutMoneyDetails);
            userTradeSummaryAssemService.batchSave(userTradeSummaries);

            if (operateWithDrawReqService.saveSuc(sucReqs)) {
                //todo 修改状态
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
        List<OperateWithDrawReq> reqList = operateWithDrawReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = operateWithDrawReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.operateWithDrawReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.operateWithDrawReq.name());
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
        List<OperateWithDrawReq> failedReqs = operateWithDrawReqService.getSaveFailed(startTime, endTime);
        if (failedReqs != null && failedReqs.size() > 0) {
            logger.info("------mongoFailed ,operateWithDraw , reqIds.size :"+ failedReqs.size()+" , startTime : "+ startTime+" endTime :" + endTime);
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
        List<Long> reqIds = operateWithDrawReqService.getSucIds(startTime, endTime);
        SpringDataPageable pageable = new SpringDataPageable();
        pageable.setSort(new Sort("reqId"));
        pageable.setPagesize(CriusConstants.MONGO_NO_PROC_SIZE);
        pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_WITHDRAW, hhDate)));
        List<OperateWithDrawReq> withDrawReqs = operateWithDrawReqService.getNotProc(startTime, endTime, reqIds, pageable);
        while (withDrawReqs != null && withDrawReqs.size() > 0) {
            logger.info("------mongoNoProc ,operateWithDraw  , noProcSize : " + withDrawReqs.size() + ", startTime : " + startTime + " endTime :" + endTime);
            flushData(withDrawReqs);
            pageable.setPagenumber(baseReqService.getNoProcPage(RedisConstants.getNoProcPage(RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_WITHDRAW, hhDate)));
            withDrawReqs = operateWithDrawReqService.getNotProc(startTime, endTime, reqIds, pageable);
        }
    }


    private OwnerOperateOutDetail assembleOwnerOperateOutDetail(OperateWithDrawReq req) {
        OwnerOperateOutDetail detail = new OwnerOperateOutDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setOperateOutMoneyCount(req.getAmount());
        //TODO 出款次数
        detail.setOperateOutNum(1);
        detail.setOperateOutType(req.getWithdrawType());
        detail.setOperateOutTypeName(req.getRemark());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return detail;
    }
    
    private OperateWithDrawReq assembleSucReq(OperateWithDrawReq req) {
        OperateWithDrawReq sucReq = new OperateWithDrawReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }

}
