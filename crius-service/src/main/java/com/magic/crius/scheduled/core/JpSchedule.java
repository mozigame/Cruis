package com.magic.crius.scheduled.core;

import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.JpReqConsumer;
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
public class JpSchedule {


    /*彩金（成功）*/
    @Resource
    private JpReqConsumer jpReqConsumer;
    @Resource
    private BaseReqService baseReqService;

    /**
     * 彩金
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void jpSchedule() {
        //如果没有开启定时任务的开关，不执行
        if (!baseReqService.getScheduleSwitch()) {
            return;
        }
        jpReqConsumer.init(new Date());
    }
}
