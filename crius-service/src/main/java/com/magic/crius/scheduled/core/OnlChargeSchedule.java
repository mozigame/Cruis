package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.OnlChargeReqConsumer;
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
public class OnlChargeSchedule {


    /*用户充值成功*/
    @Resource
    private OnlChargeReqConsumer onlChargeReqConsumer;

    /**
     * 用户充值成功
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void onlChargeSchedule() {
        try {
            onlChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OnlCharge schedule error ,", e);
        }
    }
}
