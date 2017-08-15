package com.magic.crius.scheduled.core;

import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.JpReqConsumer;
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
public class JpSchedule {


    /*彩金（成功）*/
    @Resource
    private JpReqConsumer jpReqConsumer;

    /**
     * 彩金
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void jpSchedule() {
        jpReqConsumer.init(new Date());
    }
}
