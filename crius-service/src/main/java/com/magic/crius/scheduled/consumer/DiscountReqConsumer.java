package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerPreferentialDetailAssemService;
import com.magic.crius.assemble.UserPreferentialDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;
import static com.magic.crius.constants.ScheduleConsumerConstants.THREAD_SIZE;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 14:07
 */
@Component
public class DiscountReqConsumer {


    private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();

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
    @Resource
    private UserTradeAssemService userTradeAssemService;


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
        List<DiscountReq> reqList = discountReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            System.out.println(reqList.size() + "   thread "+ Thread.currentThread().getName());
            flushData(reqList);
            reqList = discountReqService.batchPopRedis(date);
            try {
                Thread.sleep(CriusConstants.POLL_POP_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            List<UserTrade> userTrades = new ArrayList<>();
            List<DiscountReq> sucReqs = new ArrayList<>();
            for (DiscountReq req : list) {
                /*会员优惠汇总*/
                ownerOnlineFlowDetailMap.add(assembleOwnerPreferentialDetail(req));
                /*会员优惠汇总*/
                userPreferentialDetailHashMap.add(assembleUserPreferentialDetail(req));
                /*账户交易明细*/
                userTrades.add(userTradeAssemService.assembleUserTrade(req));
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));

            }
            ownerPreferentialDetailAssemService.batchSave(ownerOnlineFlowDetailMap);
            userPreferentialDetailAssemService.batchSave(userPreferentialDetailHashMap);
            userTradeAssemService.batchSave(userTrades);
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
        List<DiscountReq> failedReqs = discountReqService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = discountReqService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<DiscountReq> withDrawReqs = discountReqService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }

    private OwnerPreferentialDetail assembleOwnerPreferentialDetail(DiscountReq req) {
        OwnerPreferentialDetail ownerPreferentialDetail = new OwnerPreferentialDetail();
        ownerPreferentialDetail.setOwnerId(req.getOwnerId());
        ownerPreferentialDetail.setPreferentialMoneyCount(req.getAmount());
        ownerPreferentialDetail.setPreferentialNum(1);
        ownerPreferentialDetail.setPreferentialType(req.getStatus());
        //TODO name 在何处获取
        ownerPreferentialDetail.setPreferentialTypeName("");
        ownerPreferentialDetail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return ownerPreferentialDetail;
    }

    private UserPreferentialDetail assembleUserPreferentialDetail(DiscountReq req) {
        UserPreferentialDetail detail = new UserPreferentialDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setPreferentialMoneyCount(req.getAmount());
        //todo 优惠次数
        detail.setPreferentialNum(1);
        detail.setPreferentialType(req.getStatus());
        //todo 优惠类型名称
        detail.setPreferentialTypeName("");
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return detail;
    }

    private DiscountReq assembleSucReq(DiscountReq req) {
        /*成功的数据*/
        DiscountReq sucReq = new DiscountReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }
}
