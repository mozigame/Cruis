package com.magic.crius.scheduled.core;

import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.CashbackReqConsumer;
import com.magic.crius.service.BaseReqService;
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
public class CashbackSchedule {


    /*返水（成功）*/
    @Resource
    private CashbackReqConsumer cashbackReqConsumer;

    @Resource
    private BaseReqService baseReqService;

    /**
     * 返水
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void cashbackSchedule() {
        //如果没有开启定时任务的开关，不执行
        if (!baseReqService.getScheduleSwitch()) {
            return;
        }
        cashbackReqConsumer.init(new Date());
    }
}
