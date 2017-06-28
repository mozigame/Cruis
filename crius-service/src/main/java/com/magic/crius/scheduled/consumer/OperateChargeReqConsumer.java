package com.magic.crius.scheduled.consumer;

import com.magic.analysis.enums.ActionType;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerOperateFlowDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.OperateChargeReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

import static com.magic.crius.constants.ScheduleConsumerConstants.POLL_TIME;
import static com.magic.crius.constants.ScheduleConsumerConstants.THREAD_SIZE;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 14:18
 */
@Service
public class OperateChargeReqConsumer {

    private static final Logger logger = Logger.getLogger(OperateChargeReqConsumer.class);

    private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private RepairLockService repairLockService;

    @Resource
    private OperateChargeReqService operateChargeService;
    /*人工入款汇总*/
    @Resource
    private OwnerOperateFlowDetailAssemService ownerOperateFlowDetailAssemService;
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
        List<OperateChargeReq> reqList = operateChargeService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            logger.debug("operateChargeReqConsumer pop datas, size : "+reqList.size());
            flushData(reqList);
            reqList = operateChargeService.batchPopRedis(date);
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
    private void flushData(Collection<OperateChargeReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerOperateFlowDetail> ownerOperateFlowSummmaries = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            List<OperateChargeReq> sucReqs = new ArrayList<>();
            for (OperateChargeReq req : list) {
                /**
                 * 人工入款汇总
                 */
                ownerOperateFlowSummmaries.add(assembleOwnerOperateFlowDetail(req));

                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (int i = 0; i < req.getUserIds().length; i++) {
                        userTrades.add(userTradeAssemService.assembleUserTrade(req, req.getUserIds()[i], req.getBillIds()[i]));
                    }
                }

                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));

            }
            ownerOperateFlowDetailAssemService.batchSave(ownerOperateFlowSummmaries);
            userTradeAssemService.batchSave(userTrades);
            //todo 成功的id处理
            if (!operateChargeService.saveSuc(sucReqs)) {

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
        List<OperateChargeReq> reqList = operateChargeService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = operateChargeService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.operateChargeReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.operateChargeReq.name());
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
        List<OperateChargeReq> failedReqs = operateChargeService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = operateChargeService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<OperateChargeReq> withDrawReqs = operateChargeService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }

    private OwnerOperateFlowDetail assembleOwnerOperateFlowDetail(OperateChargeReq req) {
        OwnerOperateFlowDetail summmary = new OwnerOperateFlowDetail();
        summmary.setOwnerId(req.getOwnerId());
        //todo
        summmary.setOperateFlowNum(1);
        summmary.setOperateFlowMoneyCount(req.getChargeAmount());
        summmary.setOperateFlowType(req.getType());
        summmary.setOperateFlowTypeName(req.getRemark());
        summmary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return summmary;
    }


    private OperateChargeReq assembleSucReq(OperateChargeReq req) {
        /*成功的数据*/
        OperateChargeReq sucReq = new OperateChargeReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }


}
