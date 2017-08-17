package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.VGameReq;
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
 * Time: 19:19
 */
@Component
public class VGameKfConsumer {


    private static Logger logger = Logger.getLogger(VGameKfConsumer.class);


    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;

    /*注单*/
    @Resource
    private BaseOrderReqAssemService baseGameReqAssemService;

    /**
     * vGame
     * @param record
     */
    @KafkaListener(topics = {"VGAME"}, group = KafkaConf.CAPITAL_GROUP)
    public void vGameListen(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,get vgame kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                VGameReq vGameReq = JSON.parseObject(object.getString(KafkaConf.RECORD), VGameReq.class);
                vGameReq.setProduceTime(vGameReq.getInsertDatetime());
                vGameReq.setOrderExtent(convertVGameExt(vGameReq));
                vGameReq.setConsumerTime(System.currentTimeMillis());
                baseGameReqAssemService.procKafkaData(vGameReq);
            }
        } catch (Exception e) {
            ApiLogger.error("proceData vGame error , ", e);
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

}
