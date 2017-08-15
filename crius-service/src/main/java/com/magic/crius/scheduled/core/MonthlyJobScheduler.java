package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.scheduled.consumer.MonthJobConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:32
 */
@Component
public class MonthlyJobScheduler {

    @Resource
    private MonthJobConsumer monthJobConsumer;

    /**
     * 每天凌晨2点执行定时任务发起请求
     *
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void monthlyJobRun() {
        ApiLogger.info("monthly job run start.");
        try {
            monthJobConsumer.init();
            ApiLogger.info("monthly job run end.");
        } catch (Exception e) {
            ApiLogger.error("monthly job error,", e);
        }
    }

}
