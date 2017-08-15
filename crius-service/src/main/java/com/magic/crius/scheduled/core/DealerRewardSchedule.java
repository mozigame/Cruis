package com.magic.crius.scheduled.core;

import com.magic.crius.constants.ScheduleConsumerConstants;
import com.magic.crius.scheduled.consumer.DealerRewardReqConsumer;
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
public class DealerRewardSchedule {


    /*打赏（成功）*/
    @Resource
    private DealerRewardReqConsumer dealerRewardReqConsumer;

    /**
     * 打赏
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.cacheFlushRate)
    public void dealerRewardSchedule() {
        dealerRewardReqConsumer.init(new Date());
    }

}
