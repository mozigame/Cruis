package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.*;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.vo.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 1:38
 *
 */
@Component
public class CapitalConsumer {

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

    @KafkaListener(topics = "cruis_capital", group = "group_1" )
    public void listen(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                transData(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换kafka数据
     * @param record
     */
    private void transData(ConsumerRecord<?, ?> record) {
        System.out.println("get kafka data :>>>  "+ record.toString());
        JSONObject object = JSON.parseObject(record.value().toString());
        KafkaConf.DataType type = KafkaConf.DataType.parse(object.getInteger("DataType"));
        switch (type) {
            case PLUTUS_ONL_CHARGE:
                OnlChargeReq onlChargeReq = JSON.parseObject(object.getString("Data"), OnlChargeReq.class);
                onlChargeAssemService.procKafkaData(onlChargeReq);
                break;
            case PLUTUS_CMP_CHARGE:
                PreCmpChargeReq preCmpChargeReq = JSON.parseObject(object.getString("Data"), PreCmpChargeReq.class);
                preCmpChargeAssembleService.procKafkaData(preCmpChargeReq);
                break;
            case PLUTUS_DISCOUNT:
                DiscountReq discountReq = JSON.parseObject(object.getString("Data"), DiscountReq.class);
                discountReqAssemService.procKafkaData(discountReq);
                break;
            case PLUTUS_USER_WITHDRAW:
                PreWithdrawReq preWithdrawReq = JSON.parseObject(object.getString("Data"), PreWithdrawReq.class);
                preWithdrawReqAssemService.procKafkaData(preWithdrawReq);
                break;
            case PLUTUS_OPR_WITHDRAW:
                OperateWithDrawReq operateWithDrawReq = JSON.parseObject(object.getString("Data"), OperateWithDrawReq.class);
                operateWithDrawReqAssemService.procKafkaData(operateWithDrawReq);
                break;
            case PLUTUS_OPR_CHARGE:
                OperateChargeReq operateChargeReq = JSON.parseObject(object.getString("Data"), OperateChargeReq.class);
                operateChargeReqAssemService.procKafkaData(operateChargeReq);
                break;
            case PLUTUS_CAHSBACK:
                CashbackReq payoffReq = JSON.parseObject(object.getString("Data"), CashbackReq.class);
                cashbackReqAssemService.procKafkaData(payoffReq);
                break;
            case PLUTUS_PAYOFF:
                PayoffReq cashbackReq = JSON.parseObject(object.getString("Data"), PayoffReq.class);
                break;
            case PLUTUS_JP:
                JpReq jpReq = JSON.parseObject(object.getString("Data"), JpReq.class);
                jpReqAssemService.procKafkaData(jpReq);
                break;
            case PLUTUS_DS:
                DealerRewardReq dealerRewardReq = JSON.parseObject(object.getString("Data"), DealerRewardReq.class);
                dealerRewardReqAssemService.procKafkaData(dealerRewardReq);
                break;
            default:
                break;

        }
    }

}
