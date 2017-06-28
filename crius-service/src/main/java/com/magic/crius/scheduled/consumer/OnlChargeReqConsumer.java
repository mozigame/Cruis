package com.magic.crius.scheduled.consumer;

import com.magic.analysis.enums.ActionType;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.MemberConditionVoAssemService;
import com.magic.crius.assemble.UserFlowMoneyDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.assemble.OwnerOnlineFlowDetailAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.user.vo.MemberConditionVo;
import org.apache.log4j.Logger;
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

    private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;

    @Resource
    private OnlChargeReqService onlChargeService;
    /*线上入款汇总*/
    @Resource
    private OwnerOnlineFlowDetailAssemService ownerOnlineFlowDetailAssemService;
    /*会员入款明细*/
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;
    @Resource
    private MemberConditionVoAssemService memberConditionVoAssemService;


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
        List<OnlChargeReq> reqList = onlChargeService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            System.out.println("onlChargeReqConsumer pop datas, size : "+reqList.size());
            flushData(reqList);
            reqList = onlChargeService.batchPopRedis(date);
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
    private void flushData(Collection<OnlChargeReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerOnlineFlowDetail> ownerOnlineFlowSummmaries = new ArrayList<>();
            List<UserFlowMoneyDetail> userFlowMoneyDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<OnlChargeReq> sucReqs = new ArrayList<>();
            Map<Long, MemberConditionVo> memberConditionVoMap = new HashMap<>();
            for (OnlChargeReq req : list) {
                /*线上入款汇总*/
                ownerOnlineFlowSummmaries.add(assembleOwnerOnlineFlowDetail(req));
                /*会员入款详情*/
                userFlowMoneyDetails.add(assembleUserFlowMoneyDetail(req));
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

                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            ownerOnlineFlowDetailAssemService.batchSave(ownerOnlineFlowSummmaries);
            userFlowMoneyDetailAssemService.batchSave(userFlowMoneyDetails);
            userTradeAssemService.batchSave(userTrades);
            memberConditionVoAssemService.batchRecharge(memberConditionVoMap.values());
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
        List<OnlChargeReq> failedReqs = onlChargeService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = onlChargeService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<OnlChargeReq> withDrawReqs = onlChargeService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
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

    private UserFlowMoneyDetail assembleUserFlowMoneyDetail(OnlChargeReq req) {
        UserFlowMoneyDetail detail = new UserFlowMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setMerchantCode(req.getMerchantCode());
        detail.setMerchantName(req.getMerchantName());
        detail.setOrderCount(req.getChargeAmount());
        //Todo 待确定
        detail.setState(0);
        //todo 待确定
        detail.setPayMethod(123);
        detail.setFlowId(req.getOrderId());
        //TODO 待确定
        detail.setFlowType(ActionType.CHONG_ZHI.getStatus());
        detail.setOrderId(req.getOrderId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        return detail;
    }

    private OnlChargeReq assembleSucReq(OnlChargeReq req) {
        /*成功的数据*/
        OnlChargeReq sucReq = new OnlChargeReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }
}
