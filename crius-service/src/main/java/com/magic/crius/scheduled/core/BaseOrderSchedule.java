package com.magic.crius.scheduled.core;

import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.BaseOrderReqConsumer;
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
public class BaseOrderSchedule {


    /*用户订单*/
    @Resource
    private BaseOrderReqConsumer baseOrderReqConsumer;

    /**
     * 订单
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void baseOrderSchedule() {
        baseOrderReqConsumer.init(new Date());
    }
}
