package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerCompanyAccountDetailAssemService;
import com.magic.crius.assemble.OwnerCompanyFlowDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.enums.SummaryKind;
import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.po.OwnerCompanyFlowDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;
import static com.magic.crius.constants.ScheduleConsumerConstants.THREAD_SIZE;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 17:03
 * 公司入款
 */
@Component
public class PreCmpChargeReqConsumer {


    private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 4, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private PreCmpChargeReqService preCmpChargeService;
    @Resource
    private OwnerCompanyFlowDetailAssemService ownerCompanyFlowDetailAssemService;
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Resource
    private RepairLockService repairLockService;


    public void procKafkaData(PreCmpChargeReq req) {
        if (preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                CriusLog.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        }
    }


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            userOutMoneyTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    currentDataCalculate(date);
                }
            });
        }

        userOutMoneyHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                repairCacheHistoryTask(date);
                repairMongoAbnormal(date);
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
        List<PreCmpChargeReq> reqList = preCmpChargeService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            flushData(reqList);
            reqList = preCmpChargeService.batchPopRedis(date);
        }
    }

    /**
     * 清洗数据入库
     *
     * @param list
     */
    private void flushData(Collection<PreCmpChargeReq> list) {
        if (list != null && list.size() > 0) {

            List<OwnerCompanyFlowDetail> ownerCompanyFlowDetails = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (PreCmpChargeReq req : list) {
                /*公司入款明细*/
                ownerCompanyFlowDetails.add(assembleOwnerCompanyFlowDetail(req));
                /*公司账目汇总*/
                ownerCompanyAccountDetails.add(assembleOwnerCompanyAccountDetail(req));
                userTrades.add(assembleUserTrade(req));
            }
            ownerCompanyFlowDetailAssemService.batchSave(ownerCompanyFlowDetails);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            userTradeAssemService.batchSave(userTrades);

            //todo 成功的id处理
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
        List<PreCmpChargeReq> reqList = preCmpChargeService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = preCmpChargeService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.preCmpChargeReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.operateWithDrawReq.name());
        repairLock.setValue(CriusConstants.REPAIR_LOCK_VALUE);
        if (repairLockService.save(repairLock)) {
            mongoFailed(startDate.getTimeInMillis(), endDate.getTime());
            mongoNoProc(startDate.getTimeInMillis(), endDate.getTime());
        }

    }

    /**
     * mongo插入失败数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoFailed(Long startTime, Long endTime) {
        List<PreCmpChargeReq> failedReqs = preCmpChargeService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = preCmpChargeService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<PreCmpChargeReq> withDrawReqs = preCmpChargeService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }


    private OwnerCompanyFlowDetail assembleOwnerCompanyFlowDetail(PreCmpChargeReq req) {
        OwnerCompanyFlowDetail flow = new OwnerCompanyFlowDetail();
        flow.setOwnerId(req.getOwnerId());
        flow.setCompanyFlowMoneyCount(req.getAmount());
        flow.setCompanyFlowNum(1);
        flow.setAccountNum(req.getInBankNum());
        flow.setAccountName(req.getBankHolder());
        flow.setBankSystemCode(req.getInBankCode());
        flow.setState(0);
        flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //TODO 暂时为空
        flow.setAccountCode(0L);
        //todo
        flow.setBankSystemName("");
        return flow;
    }

    private OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(PreCmpChargeReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(111);
        account.setSummaryTypeName("线上入款");
        account.setSummaryKind(SummaryKind.income.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }

    private UserTrade assembleUserTrade(PreCmpChargeReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(0);
        //todo 存取类型
        userTrade.setActiontype(0);
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

}
