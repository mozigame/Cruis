package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.LotteryReq;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: joey
 * Date: 2017/8/3
 * Time: 19:22
 */
@Component
public class LotteryKfConsumer {

    private static Logger logger = Logger.getLogger(LotteryKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;
    /*注单*/
    @Resource
    private BaseOrderReqAssemService baseGameReqAssemService;
    private static AtomicLong counter=new AtomicLong();

    /**
     * LOTTERY
     * @param record
     */
    @KafkaListener(topics = {"LOTTERY"}, group = KafkaConf.CAPITAL_GROUP)
    public void lotteryListen(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,get lottery kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                LotteryReq lotteryReq = JSON.parseObject(object.getString(KafkaConf.RECORD), LotteryReq.class);
                lotteryReq.setProduceTime(lotteryReq.getInsertDatetime());
                lotteryReq.setOrderExtent(convertLotteryExt(lotteryReq));
                lotteryReq.setConsumerTime(System.currentTimeMillis());
                baseGameReqAssemService.procKafkaData(lotteryReq);
                Long count=counter.incrementAndGet();
                if(count%1000==0){
                    logger.info("-----lottery-count="+count);
                }
            }
        } catch (Exception e) {
            ApiLogger.error("proceData lottery error , ", e);
        }
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

}
