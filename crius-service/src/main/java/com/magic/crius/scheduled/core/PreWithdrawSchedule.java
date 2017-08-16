package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.PreWithdrawReqConsumer;
import com.magic.crius.service.BaseReqService;
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
    @Resource
    private BaseReqService baseReqService;

    /**
     * 用户提现
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void preWithdrawSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            preWithdrawReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("PreWithdraw error , ",e);
        }
    }
}
