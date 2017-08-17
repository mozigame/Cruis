package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.PreCmpChargeReqConsumer;
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
public class PreCmpChargeSchedule {


    /*公司入款*/
    @Resource
    private PreCmpChargeReqConsumer preCmpChargeReqConsumer;
    @Resource
    private BaseReqService baseReqService;

    /**
     * 公司入款
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void preCmpChargeSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            preCmpChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("proceData Egame error , ", e);
        }
    }
}
