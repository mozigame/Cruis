package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.BaseOrderReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 16:46
 */
@Component
public class BaseOrderReqConsumer {

    private static final Logger logger = Logger.getLogger(BaseOrderReqConsumer.class);
    private ExecutorService baseOrderTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService baseOrderHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;


    @Resource
    private BaseOrderReqService baseOrderReqService;
    @Resource
    private UserOrderDetailAssemService userOrderDetailAssemService;


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < ScheduleConsumerConstants.THREAD_SIZE; i++) {
            baseOrderTaskPool.execute(new Runnable() {
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

        baseOrderHistoryTaskPool.execute(new Runnable() {
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
        List<BaseOrderReq> reqList = baseOrderReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.info("baseOrderReqConsumer pop datas, size : " + reqList.size());
            flushData(reqList);
            reqList = baseOrderReqService.batchPopRedis(date);
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
    private void flushData(Collection<BaseOrderReq> list) {
        if (list != null && list.size() > 0) {
            List<UserOrderDetail> details = new ArrayList<>();
            List<BaseOrderReq> sucReqs = new ArrayList<>();
            for (BaseOrderReq req : list) {
                details.add(assembleUserOrderDetail(req));
                    /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }
            userOrderDetailAssemService.batchSave(details);
            //todo 成功的id处理
            if (!baseOrderReqService.saveSuc(sucReqs)) {

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
        List<BaseOrderReq> reqList = baseOrderReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = baseOrderReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.baseOrderReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.baseOrderReq.name());
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
        List<BaseOrderReq> failedReqs = baseOrderReqService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = baseOrderReqService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<BaseOrderReq> withDrawReqs = baseOrderReqService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }

    private UserOrderDetail assembleUserOrderDetail(BaseOrderReq req) {
        UserOrderDetail detail = new UserOrderDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBcBetId());
        detail.setOrderCount(req.getBetAmount());
        detail.setEffectOrderCount(req.getValidBetAmount());
        detail.setPayOffCount(req.getPayoff());
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getUpdateDatetime()), "yyyyMMdd")));
        detail.setCreateTime(req.getBetDatetime());
        detail.setUpdateTime(req.getUpdateDatetime());
        if (req.getIsPaid() != null) {
            detail.setIsPaid(IsPaidType.parse(req.getIsPaid()).value());
        }
        detail.setOrderExtent(req.getOrderExtent().toJSONString());
        return detail;
    }

    private BaseOrderReq assembleSucReq(BaseOrderReq req) {
        /*成功的数据*/
        BaseOrderReq sucReq = new BaseOrderReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }
}



