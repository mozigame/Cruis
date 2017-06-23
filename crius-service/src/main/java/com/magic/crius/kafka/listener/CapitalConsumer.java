package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.*;
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
 */
@Component
public class CapitalConsumer {


    private static final String RECORD = "Record";


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
    /*注单*/
    @Resource
    private BaseOrderReqAssemService baseGameReqAssemService;
    /*用户层级修改*/
    @Resource
    private UserLevelAssemService userLevelAssemService;

    @KafkaListener(topics = {"plutus", "hera"}, group = KafkaConf.CAPITAL_GROUP)
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
     * eGame
     * @param record
     */
    @KafkaListener(topics = {"EGAME","egame"}, group = KafkaConf.CAPITAL_GROUP)
    public void eGameListen(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                System.out.println("get kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                EGameReq eGameReq = JSON.parseObject(object.getString(RECORD), EGameReq.class);
                eGameReq.setReqId(eGameReq.getBcBetId());
                eGameReq.setProduceTime(eGameReq.getInsertDatetime());
                eGameReq.setOrderExtent(convertEGameExt(eGameReq));
                baseGameReqAssemService.procKafkaData(eGameReq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * vGame
     * @param record
     */
    @KafkaListener(topics = {"VGAME","vgame"}, group = KafkaConf.CAPITAL_GROUP)
    public void vGameListen(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                System.out.println("get kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                VGameReq vGameReq = JSON.parseObject(object.getString(RECORD), VGameReq.class);
                vGameReq.setReqId(vGameReq.getBcBetId());
                vGameReq.setProduceTime(vGameReq.getInsertDatetime());
                vGameReq.setOrderExtent(convertVGameExt(vGameReq));
                baseGameReqAssemService.procKafkaData(vGameReq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sports
     * @param record
     */
    @KafkaListener(topics = {"SPORTS","sports"}, group = KafkaConf.CAPITAL_GROUP)
    public void sportListen(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                System.out.println("get kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                SportReq sportReq = JSON.parseObject(object.getString(RECORD), SportReq.class);
                sportReq.setReqId(sportReq.getBcBetId());
                sportReq.setProduceTime(sportReq.getInsertDatetime());
                sportReq.setOrderExtent(convertSportExt(sportReq));
                baseGameReqAssemService.procKafkaData(sportReq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * LOTTERY
     * @param record
     */
    @KafkaListener(topics = {"LOTTERY","lottery"}, group = KafkaConf.CAPITAL_GROUP)
    public void lotteryListen(ConsumerRecord<?, ?> record) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                System.out.println("get kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                LotteryReq lotteryReq = JSON.parseObject(object.getString(RECORD), LotteryReq.class);
                lotteryReq.setReqId(lotteryReq.getBcBetId());
                lotteryReq.setProduceTime(lotteryReq.getInsertDatetime());
                lotteryReq.setOrderExtent(convertLotteryExt(lotteryReq));
                baseGameReqAssemService.procKafkaData(lotteryReq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换kafka数据
     *
     * @param record
     */
    private void transData(ConsumerRecord<?, ?> record) {
        System.out.println("get kafka data :>>>  " + record.toString());
        JSONObject object = JSON.parseObject(record.value().toString());
        KafkaConf.DataType type = KafkaConf.DataType.parse(object.getInteger(KafkaConf.DATA_TYPE));
        switch (type) {
            case PLUTUS_ONL_CHARGE:
                OnlChargeReq onlChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), OnlChargeReq.class);
                onlChargeReqAssemService.procKafkaData(onlChargeReq);
                break;
            case PLUTUS_CMP_CHARGE:
                PreCmpChargeReq preCmpChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), PreCmpChargeReq.class);
                preCmpChargeReqAssemService.procKafkaData(preCmpChargeReq);
                break;
            case PLUTUS_DISCOUNT:
                DiscountReq discountReq = JSON.parseObject(object.getString(KafkaConf.DATA), DiscountReq.class);
                discountReqAssemService.procKafkaData(discountReq);
                break;
            case PLUTUS_USER_WITHDRAW:
                PreWithdrawReq preWithdrawReq = JSON.parseObject(object.getString(KafkaConf.DATA), PreWithdrawReq.class);
                preWithdrawReqAssemService.procKafkaData(preWithdrawReq);
                break;
            case PLUTUS_OPR_WITHDRAW:
                OperateWithDrawReq operateWithDrawReq = JSON.parseObject(object.getString(KafkaConf.DATA), OperateWithDrawReq.class);
                operateWithDrawReqAssemService.procKafkaData(operateWithDrawReq);
                break;
            case PLUTUS_OPR_CHARGE:
                OperateChargeReq operateChargeReq = JSON.parseObject(object.getString(KafkaConf.DATA), OperateChargeReq.class);
                operateChargeReqAssemService.procKafkaData(operateChargeReq);
                break;
            case PLUTUS_CAHSBACK:
                CashbackReq payoffReq = JSON.parseObject(object.getString(KafkaConf.DATA), CashbackReq.class);
                cashbackReqAssemService.procKafkaData(payoffReq);
                break;
            case PLUTUS_PAYOFF:
                PayoffReq cashbackReq = JSON.parseObject(object.getString(KafkaConf.DATA), PayoffReq.class);
                break;
            case PLUTUS_JP:
                JpReq jpReq = JSON.parseObject(object.getString(KafkaConf.DATA), JpReq.class);
                jpReqAssemService.procKafkaData(jpReq);
                break;
            case PLUTUS_DS:
                DealerRewardReq dealerRewardReq = JSON.parseObject(object.getString(KafkaConf.DATA), DealerRewardReq.class);
                dealerRewardReqAssemService.procKafkaData(dealerRewardReq);
                break;
            case UPDATE_USER_LEVEL:
                UserLevelReq userLevelReq = JSON.parseObject(object.getString(KafkaConf.DATA), UserLevelReq.class);
                userLevelAssemService.updateLevel(userLevelReq);
                break;
            default:
                break;

        }
    }

    private JSONObject convertVGameExt(VGameReq req) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", req.getDetail());
        jsonObject.put("result", req.getResult());
        jsonObject.put("gameType", req.getGameType());
        jsonObject.put("serialId", req.getSerialId());
        jsonObject.put("roundno", req.getRoundno());
        jsonObject.put("tableCode", req.getTableCode());
        return jsonObject;
    }

    private JSONObject convertEGameExt(EGameReq req) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", req.getDetail());
        jsonObject.put("result", req.getResult());
        return jsonObject;
    }

    private JSONObject convertLotteryExt(LotteryReq req) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", req.getDetail());
        jsonObject.put("result", req.getResult());
        jsonObject.put("lotteryType", req.getLotteryType());
        jsonObject.put("playType", req.getPlayType());
        jsonObject.put("gameCode", req.getGameCode());
        return jsonObject;
    }

    private JSONObject convertSportExt(SportReq req) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", req.getDetail());
        jsonObject.put("result", req.getResult());
        jsonObject.put("playType", req.getPlayType());
        jsonObject.put("gameName", req.getGameName());
        return jsonObject;
    }


}
