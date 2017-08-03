package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.RiskEventRecordAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.RiskRecordReq;
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
 * Time: 19:18
 */
@Component
public class RiskrecKfConsumer {


    private static Logger logger = Logger.getLogger(RiskrecKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.kfPlutusThreadTaskPool;

    @Resource
    private RiskEventRecordAssemService riskEventRecordAssemService;
    /**
     * 风控数据记录
     * @param record
     */
    @KafkaListener(topics = {"RISKREC"}, group = KafkaConf.CAPITAL_GROUP)
    public void riskRecListen(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,---riskRecListen--get kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                RiskRecordReq riskRecordReq=new RiskRecordReq(object);
                riskEventRecordAssemService.procKafkaData(riskRecordReq);
            }
        } catch (Exception e) {
            logger.error("----riskRecListen--", e);
            e.printStackTrace();

        }
    }
}
