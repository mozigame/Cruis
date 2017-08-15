package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.PreWithdrawReqConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:28
 */
@Component
public class PreWithdrawSchedule {


    /*用户提现（成功）*/
    @Resource
    private PreWithdrawReqConsumer preWithdrawReqConsumer;

    /**
     * 用户提现
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void preWithdrawSchedule() {
        try {
            preWithdrawReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("PreWithdraw error , ",e);
        }
    }
}
