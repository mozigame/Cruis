package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.MemberConditionVoAssemService;
import com.magic.crius.assemble.OwnerCompanyAccountDetailAssemService;
import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.*;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.PreWithdrawReq;
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
 * Time: 16:28
 */
@Component
public class PreWithdrawReqConsumer {

    private static final Logger logger = Logger.getLogger(PreWithdrawReqConsumer.class);

    private ExecutorService preWithDrawTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService preWithDrawHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;

    /**
     * 用户提现
     */
    @Resource
    private PreWithdrawReqService preWithdrawService;
    /*会员出款明细*/
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;
    /*公司账目汇总*/
    @Resource
    private OwnerCompanyAccountDetailAssemService ownerCompanyAccountDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;
    @Resource
    private MemberConditionVoAssemService memberConditionVoAssemService;


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            preWithDrawTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
						currentDataCalculate(date);
					} catch (Exception e) {
						logger.error("---detailCalculate--", e);
					}
                }
            });
        }

        preWithDrawHistoryTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
					repairCacheHistoryTask(date);
					repairMongoAbnormal(date);
				} catch (Exception e) {
					logger.error("---detailCalculate-task--", e);
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
        List<PreWithdrawReq> reqList = preWithdrawService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            System.out.println("preWithdrawReqConsumer pop datas, size : "+reqList.size());
            flushData(reqList);
            reqList = preWithdrawService.batchPopRedis(date);
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
    private void flushData(Collection<PreWithdrawReq> list) {
        if (list != null && list.size() > 0) {
            List<UserOutMoneyDetail> details = new ArrayList<>();
            List<OwnerCompanyAccountDetail> ownerCompanyAccountDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<PreWithdrawReq> sucReqs = new ArrayList<>();
            Map<Long, MemberConditionVo> memberConditionVoMap = new HashMap<>();
            for (PreWithdrawReq req : list) {
                /*会员出款明细*/
                details.add(assembleUserOutMoneyDetail(req));
                /*公司账目汇总*/
                ownerCompanyAccountDetails.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail(req));
				if (req.getNeedPayAmount() != null && req.getNeedPayAmount() > 0) {
					ownerCompanyAccountDetails
							.add(ownerCompanyAccountDetailAssemService.assembleOwnerCompanyAccountDetail4TaxCount(req));
				}


                userTrades.add(userTradeAssemService.assembleUserTrade(req));
                /*会员提款*/
                if (memberConditionVoMap.get(req.getUserId()) == null) {
                    memberConditionVoMap.put(req.getUserId(), memberConditionVoAssemService.assembleWithdrawMVo(req));
                } else {
                    MemberConditionVo vo  = memberConditionVoMap.get(req.getUserId());
                    vo.setWithdrawCount(vo.getWithdrawCount() + 1);
                    vo.setWithdrawMoney(vo.getWithdrawMoney() + req.getRealWithdrawAmount());
                }


                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            userOutMoneyDetailAssemService.batchSave(details);
            userTradeAssemService.batchUpdate(userTrades);
          
            memberConditionVoAssemService.batchWithdraw(memberConditionVoMap.values());
            ownerCompanyAccountDetailAssemService.batchSave(ownerCompanyAccountDetails);
            //todo  添加成功id
            if (!preWithdrawService.saveSuc(sucReqs)) {

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
        List<PreWithdrawReq> reqList = preWithdrawService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = preWithdrawService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.preWithdrawReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.preWithdrawReq.name());
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
        List<PreWithdrawReq> failedReqs = preWithdrawService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = preWithdrawService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<PreWithdrawReq> withDrawReqs = preWithdrawService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }

    private UserOutMoneyDetail assembleUserOutMoneyDetail(PreWithdrawReq req) {
        UserOutMoneyDetail detail = new UserOutMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setOrderCount(req.getRealWithdrawAmount());
        //TODO kevin提供
        detail.setTaxCount(req.getNeedPayAmount());
        //TODO 待定
        detail.setState(0);
        detail.setOrderId(req.getBillId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setHandlerId(req.getHandlerId());
        detail.setHandlerName(req.getHandlerName());
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());

        //会员出款扣款
        detail.setCostAmount(req.getCostAmount() == null ? 0: req.getCostAmount());
        detail.setFeeAmount(req.getFeeAmount() == null ? 0: req.getFeeAmount());
        detail.setOfferAmount(req.getOfferAmount() == null ? 0: req.getOfferAmount());
        return detail;
    }

    private PreWithdrawReq assembleSucReq(PreWithdrawReq req) {
        /*成功的数据*/
        PreWithdrawReq sucReq = new PreWithdrawReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }
}
