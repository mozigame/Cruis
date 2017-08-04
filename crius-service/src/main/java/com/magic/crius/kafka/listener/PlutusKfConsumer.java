package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.*;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.scheduled.consumer.BillInfoConsumer;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * User: joey
 * Date: 2017/8/3
 * Time: 19:09
 */
@Component
public class PlutusKfConsumer {



    private static final String RECORD = "Record";

    private static Logger logger = Logger.getLogger(PlutusKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;

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
    /*用户层级修改*/
    @Resource
    private UserLevelAssemService userLevelAssemService;

    @Resource
    private BillInfoConsumer billInfoConsumer;

    @KafkaListener(topics = {"plutus", "hera"}, group = KafkaConf.CAPITAL_GROUP)
    public void listen(ConsumerRecord<?, ?> record) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                proceData(record);
            }
        });
    }


    private void proceData(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                transData(record);
            }
        } catch (Exception e) {
            ApiLogger.error("proceData plutus error , ", e);
        }
    }

    /**
     * 转换kafka数据
     *
     * @param record
     */
    private void transData(ConsumerRecord<?, ?> record) {
        logger.info("Thread : "+ Thread.currentThread().getName()+" ,get plutus kafka data :>>>  " + record.toString());
        JSONObject object = JSON.parseObject(record.value().toString());
        KafkaConf.DataType type = KafkaConf.DataType.parse(object.getInteger(KafkaConf.DATA_TYPE));
        logger.info("transData,thread="+Thread.currentThread().getId()+" type="+type);
        switch (type) {
            case PLUTUS_ONL_CHARGE:
                OnlChargeReq onlChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), OnlChargeReq.class);
                onlChargeReqAssemService.procKafkaData(onlChargeReq);
                break;
            case PLUTUS_CMP_CHARGE:
                PreCmpChargeReq preCmpChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), PreCmpChargeReq.class);
                preCmpChargeReq.setProduceTime(System.currentTimeMillis());
                preCmpChargeReqAssemService.procKafkaData(preCmpChargeReq);
                break;
            case PLUTUS_DISCOUNT:
                DiscountReq discountReq = JSON.parseObject(object.getString(KafkaConf.DATA), DiscountReq.class);
                discountReq.setProduceTime(System.currentTimeMillis());
                discountReqAssemService.procKafkaData(discountReq);
                break;
            case PLUTUS_USER_WITHDRAW:
                PreWithdrawReq preWithdrawReq = JSON.parseObject(object.getString(KafkaConf.DATA), PreWithdrawReq.class);
                preWithdrawReq.setProduceTime(System.currentTimeMillis());
                preWithdrawReqAssemService.procKafkaData(preWithdrawReq);
                break;
            case PLUTUS_OPR_WITHDRAW:
                OperateWithDrawReq operateWithDrawReq = JSON.parseObject(object.getString(KafkaConf.DATA), OperateWithDrawReq.class);
                operateWithDrawReq.setProduceTime(System.currentTimeMillis());
                operateWithDrawReqAssemService.procKafkaData(operateWithDrawReq);
                break;
            case PLUTUS_OPR_CHARGE:
                OperateChargeReq operateChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), OperateChargeReq.class);
                operateChargeReq.setProduceTime(System.currentTimeMillis());
                operateChargeReqAssemService.procKafkaData(operateChargeReq);
                break;
            case PLUTUS_CAHSBACK:
                CashbackReq payoffReq = JSON.parseObject(object.getString(KafkaConf.DATA), CashbackReq.class);
                payoffReq.setProduceTime(System.currentTimeMillis());
                cashbackReqAssemService.procKafkaData(payoffReq);
                break;
            case PLUTUS_PAYOFF:
                PayoffReq cashbackReq = JSON.parseObject(object.getString(KafkaConf.DATA), PayoffReq.class);
                break;
            case PLUTUS_JP:
                JpReq jpReq = JSON.parseObject(object.getString(KafkaConf.DATA), JpReq.class);
                jpReq.setProduceTime(System.currentTimeMillis());
                jpReqAssemService.procKafkaData(jpReq);
                break;
            case PLUTUS_DS:
                DealerRewardReq dealerRewardReq = JSON.parseObject(object.getString(KafkaConf.DATA), DealerRewardReq.class);
                dealerRewardReq.setProduceTime(System.currentTimeMillis());
                dealerRewardReqAssemService.procKafkaData(dealerRewardReq);
                break;
            case UPDATE_USER_LEVEL:
                UserLevelReq userLevelReq = JSON.parseObject(object.getString(KafkaConf.DATA), UserLevelReq.class);
                userLevelAssemService.updateLevel(userLevelReq);
                break;
            case PLUTUS_AGENT_BILL:
                AgentBillReq agentBillReq = JSON.parseObject(object.getString(KafkaConf.DATA), AgentBillReq.class);
                // billInfoService.save(agentBillReq);
                billInfoConsumer.saveProxyBillInfo(agentBillReq);
                break;
            case PLUTUS_OWNER_BILL:
                OwnerBillReq ownerBillReq = JSON.parseObject(object.getString(KafkaConf.DATA), OwnerBillReq.class);
                //billInfoService.save(ownerBillReq);PLUTUS_AGENT_BILL
                billInfoConsumer.saveOwnerBillInfo(ownerBillReq);
                break;
            default:
                break;

        }
    }
}
