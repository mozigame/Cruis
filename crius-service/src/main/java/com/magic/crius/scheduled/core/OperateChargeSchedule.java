package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.OperateChargeReqConsumer;
import com.magic.crius.service.BaseReqService;
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
    @Resource
    private BaseReqService baseReqService;


    /**
     * 人工充值
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void operateChargeSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            operateChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OperateCharge error,", e);
        }
    }
}
