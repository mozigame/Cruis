package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.*;

/**
 * 人工提现
 */
@Service
public class OperateWithDrawReqConsumer {


    private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private OperateWithDrawReqService operateWithDrawReqService;
    /*人工出款详情*/
    @Resource
    private OwnerOperateOutDetailAssemService ownerOperateOutDetailAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;

    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Resource
    private RepairLockService repairLockService;


    public void init(Date date) {
        summaryCalculate(date);
    }


    private void summaryCalculate(Date date) {
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
        List<OperateWithDrawReq> reqList = operateWithDrawReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            flushData(reqList);
            reqList = operateWithDrawReqService.batchPopRedis(date);
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
            List<UserAccountSummary> userAccountSummaries = new ArrayList<>();
            List<OperateWithDrawReq> sucReqs = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (OperateWithDrawReq req : list) {
                /*人工出款详情*/
                ownerOperateOutDetails.add(assembleOwnerOperateOutDetail(req));

                /*会员账号汇总*/
                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (Long userId : req.getUserIds()) {
                        userAccountSummaries.add(assembleUserAccountSummary(req, userId));
                        userTrades.add(assembleUserTrade(req, userId));
                    }
                }

                /*成功的数据*/
                OperateWithDrawReq sucReq = new OperateWithDrawReq();
                sucReq.setReqId(req.getReqId());
                sucReq.setProduceTime(req.getProduceTime());
                sucReqs.add(sucReq);
            }
            ownerOperateOutDetailAssemService.batchSave(ownerOperateOutDetails);
            userAccountSummaryAssemService.updateWithdraw(userAccountSummaries);
            userTradeAssemService.batchSave(userTrades);

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
        List<OperateWithDrawReq> failedReqs = operateWithDrawReqService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = operateWithDrawReqService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<OperateWithDrawReq> withDrawReqs = operateWithDrawReqService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }


    private OwnerOperateOutDetail assembleOwnerOperateOutDetail(OperateWithDrawReq req) {
        OwnerOperateOutDetail detail = new OwnerOperateOutDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setOperateOutMoneyCount(req.getAmount());
        //TODO 出款次数
        detail.setOperateOutNum(req.getUserIds().length);
        detail.setOperateOutType(req.getWithdrawType());
        detail.setOperateOutTypeName(req.getRemark());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return detail;
    }

    private UserAccountSummary assembleUserAccountSummary(OperateWithDrawReq req, Long userId) {
        UserAccountSummary summary = new UserAccountSummary();
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(userId);
        //TODO 出款次数
        summary.setOutNum(1L);
        summary.setOutCount(req.getAmount());
        //此处都默认为0
        summary.setFlowNum(0L);
        summary.setFlowCount(0L);
        summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return summary;
    }

    private UserTrade assembleUserTrade(OperateWithDrawReq req, Long userId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
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
