package com.magic.crius.scheduled.core;

import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.BaseOrderReqConsumer;
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
public class BaseOrderSchedule {


    /*用户订单*/
    @Resource
    private BaseOrderReqConsumer baseOrderReqConsumer;
    @Resource
    private BaseReqService baseReqService;
    /**
     * 订单
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void baseOrderSchedule() {
        //如果没有设置开启定时任务的开关，不执行
        if (!baseReqService.getScheduleSwitch()) {
            return;
        }
        baseOrderReqConsumer.init(new Date());
    }
}
