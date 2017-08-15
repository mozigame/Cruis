package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.OperateWithDrawReqConsumer;
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
public class OperateWithDrawSchedule {


    /*人工提现多线程处理*/
    @Resource
    private OperateWithDrawReqConsumer operateWithDrawReqConsumer;

    /**
     * 人工提现多线程处理
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void operateWithDrawConsumerSchedule() {
        try {
            operateWithDrawReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OperateWithDraw error,", e);
        }
    }


}
