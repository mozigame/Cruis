package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.po.UserTrade;
import com.magic.crius.util.ThreadTaskPoolFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * User: joey
 * Date: 2017/8/3
 * Time: 19:21
 */
@Component
public class UserTradeKfConsumer {

    private static Logger logger = Logger.getLogger(UserTradeKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.kfPlutusThreadTaskPool;

    @Resource
    private UserTradeAssemService userTradeAssemService;

    @KafkaListener(topics = {"USER_TRADE"}, group = KafkaConf.CAPITAL_GROUP)
    public void userTradeStatus(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,get userTrade kafka data :>>>  " + record.toString());
                UserTrade object = JSON.parseObject(record.value().toString(), UserTrade.class);
                object.setStatus(13);
                List<UserTrade> userTrades = Lists.newArrayList(object);
                userTradeAssemService.updateTradeStatus4Failed(userTrades);
            }
        } catch (Exception e) {
            ApiLogger.error("proceData userTrade error , ", e);
        }
    }
}
