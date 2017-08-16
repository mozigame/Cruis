package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.OperateWithDrawReqConsumer;
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
public class OperateWithDrawSchedule {


    /*人工提现多线程处理*/
    @Resource
    private OperateWithDrawReqConsumer operateWithDrawReqConsumer;
    @Resource
    private BaseReqService baseReqService;


    /**
     * 人工提现多线程处理
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void operateWithDrawConsumerSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            operateWithDrawReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OperateWithDraw error,", e);
        }
    }


}
