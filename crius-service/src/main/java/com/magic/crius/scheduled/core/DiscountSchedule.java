package com.magic.crius.scheduled.core;

import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.DiscountReqConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:25
 */
@Component
public class DiscountSchedule {

    /*优惠赠送（成功）*/
    @Resource
    private DiscountReqConsumer discountReqConsumer;

    /**
     * 优惠赠送
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void discountSchedule() {
        discountReqConsumer.init(new Date());
    }
}
