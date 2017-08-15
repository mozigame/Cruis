package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.OperateChargeReqConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:24
 */
@Component
public class OperateChargeSchedule {

    /*人工充值（成功）*/
    @Resource
    private OperateChargeReqConsumer operateChargeReqConsumer;

    /**
     * 人工充值
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void operateChargeSchedule() {
        try {
            operateChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OperateCharge error,", e);
        }
    }
}
