package com.magic.crius.scheduled.core;

import com.magic.analysis.utils.DateKit;
import com.magic.api.commons.tools.DateUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
//@Service
@Deprecated
public class CriusScheduler {


    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> kafkaTemplate;


    //    @Scheduled(fixedRate = 1000 * 10)
    public void sendKafkaMessage() {

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("cruis_capital", DateUtil.formatDateTime(new Date(), DateUtil.formatDefaultTimestamp));
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }

        });
    }



    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        Calendar firstCal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        firstCal.set(Calendar.DAY_OF_MONTH, 1);

        int i = 1;
        while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY && DateKit.formatDate(firstCal.getTime()) != DateKit.formatDate(new Date()) ){
            cal.set(Calendar.DAY_OF_MONTH, i++);
        }

        Date firstMonday = cal.getTime();
        System.out.println(DateKit.formatDate(firstMonday));
    }
}
