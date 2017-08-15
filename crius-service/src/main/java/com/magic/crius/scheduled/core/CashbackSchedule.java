package com.magic.crius.scheduled.core;

import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.CashbackReqConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:27
 */
@Component
public class CashbackSchedule {


    /*返水（成功）*/
    @Resource
    private CashbackReqConsumer cashbackReqConsumer;

    /**
     * 返水
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void cashbackSchedule() {
        cashbackReqConsumer.init(new Date());
    }
}
