package com.magic.crius.scheduled.consumer;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.service.RepairLockService;
import org.springframework.stereotype.Service;

import com.magic.crius.vo.OperateWithDrawReq;

import javax.annotation.Resource;

/**
 * 人工提现
 */
@Service
public class OperateWithDrawReqConsumer {

    /*
    * 处理缓存中数据的线程数量
    * */
    private static final int THREAD_SIZE = 4;

    /**
     * 每个线程中轮询在缓存拿取数据的次数
     */
    private static final int POLL_TIME = 10;

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
            System.out.println("countNum   :   " + countNum);
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
            for (OperateWithDrawReq req : list) {
                /*人工出款详情*/
                OwnerOperateOutDetail detail = new OwnerOperateOutDetail();
                detail.setOwnerId(req.getOwnerId());
                detail.setOperateOutMoneyCount(req.getAmount());
                //TODO 出款次数
                detail.setOperateOutNum(req.getUserIds().length);
                detail.setOperateOutType(req.getWithdrawType());
                detail.setOperateOutTypeName(req.getRemark());
                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                ownerOperateOutDetails.add(detail);

                /*会员账号汇总*/
                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (Long userId : req.getUserIds()) {
                        UserAccountSummary summary = new UserAccountSummary();
                        summary.setUserId(userId);
                        //TODO 出款次数
                        summary.setOutNum(1L);
                        summary.setOutCount(req.getAmount());
                        //此处都默认为0
                        summary.setFlowNum(0L);
                        summary.setFlowCount(0L);
                        summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                        userAccountSummaries.add(summary);
                    }
                }

                /*成功的数据*/
                OperateWithDrawReq sucReq = new OperateWithDrawReq();
                sucReq.setReqId(req.getReqId());
                sucReq.setProduceTime(req.getProduceTime());
                sucReqs.add(sucReq);
            }
            if (ownerOperateOutDetails.size() > 0) {
                ownerOperateOutDetailAssemService.batchSave(ownerOperateOutDetails);
            }
            if (userAccountSummaries.size() > 0) {
                userAccountSummaryAssemService.updateWithdraw(userAccountSummaries);
            }

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
        List<OperateWithDrawReq> reqList = operateWithDrawReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = operateWithDrawReqService.batchPopRedis(calendar.getTime());
        }
    }

    /**
     * 纠正mongo中上一个小时内添加失败的数据和未处理的数据
     */
    private void repairMongoAbnormal(Date date) {
        String hhDate = DateUtil.formatDateTime(date, "yyyyMMddHH");
        Date endDate = DateUtil.parseDate(hhDate, "yyyyMMddHH");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(endDate);
        startDate.add(Calendar.HOUR, -1);
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.operateWithDrawReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), "yyyyMMddHH")));
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

}
