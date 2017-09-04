package com.magic.crius.scheduled.core;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.scheduled.consumer.UserOrderDetailConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 22:26
 */
@Component
public class UserOrderDetailSchedule {


    @Resource
    private UserOrderDetailConsumer userOrderDetailConsumer;


    /**
     * 会员订单
     */
    @Scheduled(fixedRate = CriusInitConstants.cacheFlushRate)
    public void userOrderDetailSchedule() {
        try {
            userOrderDetailConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("OperateCharge error,", e);
        }
    }
}
