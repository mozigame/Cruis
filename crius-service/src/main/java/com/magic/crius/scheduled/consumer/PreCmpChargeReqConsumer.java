package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.*;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
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
 * Date: 2017/6/9
 * Time: 17:03
 * 公司入款
 */
@Component
public class PreCmpChargeReqConsumer {

    private static final Logger logger = Logger.getLogger(PreCmpChargeReqConsumer.class);

    private ExecutorService preCmpChargeTaskPool = new ThreadPoolExecutor(10, 20, 4, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService preCmpChargeHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;

    @Resource
    private PreCmpChargeReqService preCmpChargeService;
    @Resource
    private OwnerCompanyFlowDetailAssemService ownerCompanyFlowDetailAssemService;
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;

    @Resource
    private UserTradeAssemService userTradeAssemService;
    @Resource
    private MemberConditionVoAssemService memberConditionVoAssemService;
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;


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
            preCmpChargeTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    currentDataCalculate(date);
                }
            });
        }

        preCmpChargeHistoryTaskPool.execute(new Runnable() {
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
            System.out.println("preCmpChargeReqConsumer pop datas, size : "+reqList.size());
            flushData(reqList);
            reqList = preCmpChargeService.batchPopRedis(date);
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
    private void flushData(Collection<PreCmpChargeReq> list) {
        if (list != null && list.size() > 0) {

            List<OwnerCompanyFlowDetail> ownerCompanyFlowDetails = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserFlowMoneyDetail> userFlowMoneyDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<PreCmpChargeReq> sucReqs = new ArrayList<>();
            Map<Long, MemberConditionVo> memberConditionVoMap = new HashMap<>();
            for (PreCmpChargeReq req : list) {
                /*公司入款明细*/
                ownerCompanyFlowDetails.add(assembleOwnerCompanyFlowDetail(req));
                /*公司账目汇总*/
                ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));
                /*会员入款详情*/
                userFlowMoneyDetails.add(userFlowMoneyDetailAssemService.assembleUserFlowMoneyDetail(req));

                userTrades.add(userTradeAssemService.assembleUserTrade(req));
                /*会员入款*/
                if (memberConditionVoMap.get(req.getUserId()) == null) {
                    memberConditionVoMap.put(req.getUserId(), memberConditionVoAssemService.assembleDepositMVo(req));
                } else {
                    MemberConditionVo vo  = memberConditionVoMap.get(req.getUserId());
                    vo.setDepositCount(vo.getDepositCount() + 1);
                    vo.setDepositMoney(vo.getDepositMoney() + req.getChargeAmount());
                }

                sucReqs.add(assembleSucReq(req));
            }
            ownerCompanyFlowDetailAssemService.batchSave(ownerCompanyFlowDetails);
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            userFlowMoneyDetailAssemService.batchSave(userFlowMoneyDetails);
            memberConditionVoAssemService.batchRecharge(memberConditionVoMap.values());
            userTradeAssemService.batchUpdate(userTrades);
            //todo 成功的id处理
            if (!preCmpChargeService.saveSuc(sucReqs)) {

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
        repairLock.setCollectionName(MongoCollections.preCmpChargeReq.name());
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
        flow.setCompanyFlowMoneyCount(req.getChargeAmount());
        flow.setCompanyFlowNum(1);
        flow.setAccountNum(req.getInBankNum());
        flow.setAccountName(req.getInBankHolder());
        flow.setBankSystemCode(req.getInBankCode());
        flow.setState(0);
        flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //TODO 暂时为0
        flow.setAccountCode("0");
        flow.setPayMethod(req.getChargeType());
        flow.setBankSystemName(req.getInBankName());
        return flow;
    }



    private PreCmpChargeReq assembleSucReq(PreCmpChargeReq req) {
        /*成功的数据*/
        PreCmpChargeReq sucReq = new PreCmpChargeReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }

}
