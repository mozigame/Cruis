package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.PreCmpChargeReqConsumer;
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
public class PreCmpChargeSchedule {


    /*公司入款*/
    @Resource
    private PreCmpChargeReqConsumer preCmpChargeReqConsumer;

    /**
     * 公司入款
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void preCmpChargeSchedule() {
        try {
            preCmpChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("proceData Egame error , ", e);
        }
    }
}
