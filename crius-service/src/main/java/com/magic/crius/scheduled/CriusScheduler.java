package com.magic.crius.scheduled;

import java.util.Date;

import javax.annotation.Resource;

import com.magic.crius.assemble.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.consumer.OperateWithDrawReqConsumer;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
@Service
public class CriusScheduler {

    private static final int fixRate = 1000 * 60;

    @Resource
    private PreCmpChargeReqAssemService preCmpChargeAssemService;
    @Resource
    private PreCmpChargeReqAssemService preCmpChargeAssembleService;
    @Resource
    private OnlChargeReqAssemService onlChargeAssemService;
    @Resource
    private OperateChargeReqAssemService operateChargeAssemService;
    @Resource
    private DiscountReqAssemService discountReqAssemService;
    @Resource
    private PreWithdrawReqAssemService preWithdrawReqAssemService;
    @Resource
    private OperateWithDrawReqAssemService operateWithDrawReqAssemService;
    @Resource
    private OperateChargeReqAssemService operateChargeReqAssemService;
    @Resource
    private CashbackReqAssemService cashbackReqAssemService;
    @Resource
    private JpReqAssemService jpReqAssemService;
    @Resource
    private DealerRewardReqAssemService dealerRewardReqAssemService;



    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     *
     */
    @Scheduled(fixedRate = fixRate)
    public void demoSchedule() {
        //公司入款
        preCmpChargeAssemService.convertData(new Date());


    }
    
    @Scheduled(fixedRate = fixRate)
    public void consumerUserOutMoney() {
        preCmpChargeAssemService.convertData(new Date());
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
