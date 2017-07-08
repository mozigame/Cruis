package com.magic.crius.scheduled.consumer;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerAwardDetailAssemService;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.constants.CriusConstants;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.RepairLock;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.PreCmpChargeReq;
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
 * Time: 13:49
 */
@Component
public class DealerRewardReqConsumer {
    private static final Logger logger = Logger.getLogger(DealerRewardReqConsumer.class);

    private ExecutorService dealerRewardTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService dealerRewardHistoryTaskPool = Executors.newSingleThreadExecutor();

    @Resource
    private DealerRewardReqService dealerRewardReqService;
    /*打赏明细*/
    @Resource
    private OwnerAwardDetailAssemService ownerAwardDetailAssemService;
    @Resource
    private RepairLockService repairLockService;


    public void init(Date date) {
        detailCalculate(date);
    }


    private void detailCalculate(Date date) {
        for (int i = 0; i < THREAD_SIZE; i++) {
            dealerRewardTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    currentDataCalculate(date);
                }
            });
        }

        dealerRewardHistoryTaskPool.execute(new Runnable() {
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
        List<DealerRewardReq> reqList = dealerRewardReqService.batchPopRedis(date);
        while (reqList != null && reqList.size() > 0 && countNum++ < POLL_TIME) {
            System.out.println("dealerReqConsumer pop datas, size : "+reqList.size());
            flushData(reqList);
            reqList = dealerRewardReqService.batchPopRedis(date);
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
    private void flushData(Collection<DealerRewardReq> list) {
        if (list != null && list.size() > 0) {
            List<OwnerAwardDetail> details = new ArrayList<>();
            List<DealerRewardReq> sucReqs = new ArrayList<>();
            for (DealerRewardReq req : list) {
                details.add(assembleOwnerAwardDetail(req));
                /*成功的数据*/
                sucReqs.add(assembleSucReq(req));
            }

            ownerAwardDetailAssemService.batchSave(details);
            //todo 成功的id处理
            if (!dealerRewardReqService.saveSuc(sucReqs)) {

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
        List<DealerRewardReq> reqList = dealerRewardReqService.batchPopRedis(calendar.getTime());
        while (reqList != null && reqList.size() > 0) {
            flushData(reqList);
            reqList = dealerRewardReqService.batchPopRedis(calendar.getTime());
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
        RepairLock repairLock = repairLockService.getTimeLock(MongoCollections.dealerRewardReq.name(), Integer.parseInt(DateUtil.formatDateTime(startDate.getTime(), DateUtil.format_yyyyMMddHH)));
        if (repairLock != null) {
            return;
        }
        repairLock = new RepairLock();
        repairLock.setProduceTime(date.getTime());
        repairLock.setCollectionName(MongoCollections.dealerRewardReq.name());
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
        List<DealerRewardReq> failedReqs = dealerRewardReqService.getSaveFailed(startTime, endTime);
        flushData(failedReqs);
    }

    /**
     * mongo未处理数据处理
     *
     * @param startTime
     * @param endTime
     */
    private void mongoNoProc(Long startTime, Long endTime) {
        List<Long> reqIds = dealerRewardReqService.getSucIds(startTime, endTime);
        if (reqIds != null && reqIds.size() > 0) {
            List<DealerRewardReq> withDrawReqs = dealerRewardReqService.getNotProc(startTime, endTime, reqIds);
            flushData(withDrawReqs);
        }
    }

    private OwnerAwardDetail assembleOwnerAwardDetail(DealerRewardReq req) {
        OwnerAwardDetail detail = new OwnerAwardDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBillId());
        //TODO 待确定
        detail.setCurrencyCode("");
        //todo 待确定
        detail.setCurrencyName("");
        //Todo 待确定 游戏桌号是局号
        detail.setOfficeNum(req.getGameDeskNum().intValue());
        detail.setOrderCount(req.getRewardAmount());
        detail.setDealerCode(String.valueOf(req.getDealerId()));
        detail.setDealerName(req.getDealerName());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        return detail;
    }

    private DealerRewardReq assembleSucReq(DealerRewardReq req) {
        /*成功的数据*/
        DealerRewardReq sucReq = new DealerRewardReq();
        sucReq.setReqId(req.getReqId());
        sucReq.setProduceTime(req.getProduceTime());
        return sucReq;
    }

}
