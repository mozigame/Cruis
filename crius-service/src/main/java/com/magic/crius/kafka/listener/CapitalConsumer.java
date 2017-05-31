package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.PreCmpChargeAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.vo.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
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
    private PreCmpChargeAssemService preCmpChargeAssembleService;

    @KafkaListener(topics = "test", group = "group_1")
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
        JSONObject object = JSON.parseObject(record.value().toString());
        KafkaConf.DataType type = KafkaConf.DataType.parse(object.getString("dataType"));
        switch (type) {
            case PLUTUS_ONL_CHARGE:
                OnlChargeReq onlChargeReq = JSON.parseObject(object.getString("data"), OnlChargeReq.class);
                break;
            case PLUTUS_CMP_CHARGE:
                PreCmpChargeReq preCmpChargeReq = JSON.parseObject(object.getString("data"), PreCmpChargeReq.class);
                preCmpChargeAssembleService.procPreCmpCharge(preCmpChargeReq);
                break;
            case PLUTUS_DISCOUNT:
                DiscountReq discountReq = JSON.parseObject(object.getString("data"), DiscountReq.class);
                break;
            case PLUTUS_USER_WITHDRAW:
                PreWithdrawReq preWithdrawReq = JSON.parseObject(object.getString("data"), PreWithdrawReq.class);
                break;
            case PLUTUS_OPR_WITHDRAW:
                OperateChargeReq operateChargeReq = JSON.parseObject(object.getString("data"), OperateChargeReq.class);
                break;
            case PLUTUS_OPR_CHARGE:
                OperateWithDrawReq operateWithDrawReq = JSON.parseObject(object.getString("data"), OperateWithDrawReq.class);
                break;
            case PLUTUS_CAHSBACK:
                PayoffReq payoffReq = JSON.parseObject(object.getString("data"), PayoffReq.class);
                break;
            case PLUTUS_PAYOFF:
                CashbackReq cashbackReq = JSON.parseObject(object.getString("data"), CashbackReq.class);
                break;
            case PLUTUS_JP:
                JpReq jpReq = JSON.parseObject(object.getString("data"), JpReq.class);
                break;
            case PLUTUS_DS:
                DealerRewardReq dealerRewardReq = JSON.parseObject(object.getString("data"), DealerRewardReq.class);
                break;
            default:
                break;
        }
    }

}
