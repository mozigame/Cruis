package com.magic.crius.scheduled.core;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.*;
import com.magic.crius.scheduled.consumer.OperateWithDrawReqConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
@Service
public class CriusScheduler {

    /**
     * 处理redis数据的间隔时间
     */
    private static final int cacheFlushRate = 1000 * 30;
    /**
     * 拉取代理列表的时间间隔
     */
    private static final int proxyPullRate = 1000 * 60 * 60;

    /*公司入款（成功）*/
    @Resource
    private PreCmpChargeReqAssemService preCmpChargeReqAssemService;
    /*用户充值成功*/
    @Resource
    private OnlChargeReqAssemService onlChargeReqAssemService;
    /*优惠赠送（成功）*/
    @Resource
    private DiscountReqAssemService discountReqAssemService;
    /*用户提现（成功）*/
    @Resource
    private PreWithdrawReqAssemService preWithdrawReqAssemService;
    /*人工提现（成功）*/
    @Resource
    private OperateWithDrawReqAssemService operateWithDrawReqAssemService;
    /*人工充值（成功）*/
    @Resource
    private OperateChargeReqAssemService operateChargeReqAssemService;
    /*返水（成功）*/
    @Resource
    private CashbackReqAssemService cashbackReqAssemService;
    /*彩金（成功）*/
    @Resource
    private JpReqAssemService jpReqAssemService;
    /*打赏（成功）*/
    @Resource
    private DealerRewardReqAssemService dealerRewardReqAssemService;

    /*人工提现多线程处理*/
    @Resource
    private OperateWithDrawReqConsumer operateWithDrawReqConsumer;

    /*代理详情*/
    @Resource
    private ProxyInfoAssemService proxyInfoAssemService;
    /*用户订单*/
    @Resource
    private BaseOrderReqAssemService baseOrderReqAssemService;

    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     * 公司入款
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void preCmpChargeSchedule() {
        try {
            preCmpChargeReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户充值成功
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void onlChargeSchedule() {
        try {
            onlChargeReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人工充值
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void operateChargeSchedule() {
        try {
            operateChargeReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 优惠赠送
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void discountSchedule() {
        try {
            discountReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户提现
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void preWithdrawSchedule() {
        try {
            preWithdrawReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人工提现
     */
//    @Scheduled(fixedRate = cacheFlushRate)
    public void operateWithDrawSchedule() {
        try {
            operateWithDrawReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人工提现多线程处理
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void operateWithDrawConsumerSchedule() {
        try {
            operateWithDrawReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返水
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void cashbackSchedule() {
        try {
            cashbackReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 彩金
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void jpSchedule() {
        try {
            jpReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打赏
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void dealerRewardSchedule() {
        try {
            dealerRewardReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单
     */
    @Scheduled(fixedRate = proxyPullRate)
    public void baseGameSchedule() {
        try {
            baseOrderReqAssemService.convertData(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时获取代理列表入库
     */
    @Scheduled(fixedRate = proxyPullRate)
    public void proxyListPullSchedule() {
        try {
            proxyInfoAssemService.batchSave(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






//    @Scheduled(fixedRate = 1000 * 10)
    public void sendKafkaMessage() {

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("cruis_capital", DateUtil.formatDateTime(new Date(), DateUtil.formatDefaultTimestamp));
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }

        });
    }
}
