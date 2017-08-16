package com.magic.crius.scheduled.core;

import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.DiscountReqConsumer;
import com.magic.crius.service.BaseReqService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:25
 */
@Component
public class DiscountSchedule {

    /*优惠赠送（成功）*/
    @Resource
    private DiscountReqConsumer discountReqConsumer;
    @Resource
    private BaseReqService baseReqService;

    /**
     * 优惠赠送
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void discountSchedule() {
        //如果没有开启定时任务的开关，不执行
        if (!baseReqService.getScheduleSwitch()) {
            return;
        }
        discountReqConsumer.init(new Date());
    }
}
