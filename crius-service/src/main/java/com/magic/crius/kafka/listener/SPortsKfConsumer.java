package com.magic.crius.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.analysis.enums.GameTypeEnum;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.SportReq;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: joey
 * Date: 2017/8/3
 * Time: 19:20
 */
@Component
public class SPortsKfConsumer {

    private static Logger logger = Logger.getLogger(SPortsKfConsumer.class);

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;

    /*注单*/
    @Resource
    private BaseOrderReqAssemService baseGameReqAssemService;
    private static AtomicLong counter=new AtomicLong();
    /**
     * sports
     * @param record
     */
    @KafkaListener(topics = {"SPORTS"}, group = KafkaConf.CAPITAL_GROUP)
    public void sportListen(ConsumerRecord<?, ?> record) {
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
                logger.info("Thread : "+ Thread.currentThread().getName()+" ,get sports kafka data :>>>  " + record.toString());
                JSONObject object = JSON.parseObject(record.value().toString());
                SportReq sportReq = JSON.parseObject(object.getString(KafkaConf.RECORD), SportReq.class);
                sportReq.setProduceTime(sportReq.getInsertDatetime());
                sportReq.setOrderExtent(convertSportExt(sportReq));
                Date date = new Date();
                sportReq.setConsumerTime(date.getTime());
                sportReq.setGameAbstractType(Integer.parseInt(GameTypeEnum.PHYSICAL.getCode()));
                sportReq.setPdate(Integer.parseInt(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMdd)));
                baseGameReqAssemService.procKafkaData(sportReq);
                Long count=counter.incrementAndGet();
                if(count%1000==0){
                    logger.info("-----sports-count="+count);
                }
            }
        } catch (Exception e) {
            ApiLogger.error("proceData sports error , ", e);
        }
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
