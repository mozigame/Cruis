package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.EGameReq;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * User: joey
 * Date: 2017/8/3
 * Time: 19:11
 */
@Component
public class EGameKfConsumer {


    private static Logger logger = Logger.getLogger(EGameKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;
    /*注单*/
    @Resource
    private BaseOrderReqAssemService baseGameReqAssemService;
    /**
     * eGame
     * @param record
     */
    @KafkaListener(topics = {"EGAME"}, group = KafkaConf.CAPITAL_GROUP)
    public void eGameListen(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,get egame kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                EGameReq eGameReq = JSON.parseObject(object.getString(KafkaConf.RECORD), EGameReq.class);
                eGameReq.setReqId(eGameReq.getBcBetId());
                eGameReq.setProduceTime(System.currentTimeMillis());
                eGameReq.setOrderExtent(convertEGameExt(eGameReq));
                baseGameReqAssemService.procKafkaData(eGameReq);
            }
        } catch (Exception e) {
            ApiLogger.error("proceData Egame error , ", e);
        }
    }

    private JSONObject convertEGameExt(EGameReq req) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", req.getDetail());
        jsonObject.put("result", req.getResult());
        return jsonObject;
    }
}
