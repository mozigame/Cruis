package com.magic.crius.scheduled.core;

import com.magic.crius.assemble.UserInfoAssemService;
import com.magic.crius.assemble.UserLevelAssemService;
import com.magic.crius.constants.ScheduleConsumerConstants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:22
 */
@Component
public class UserInfoScheduler {


    /*会员层级*/
    @Resource
    private UserLevelAssemService userLevelAssemService;
    @Resource
    private UserInfoAssemService userInfoAssemService;

    /**
     * 定时更新会员层级
     */
    @Scheduled(initialDelay = ScheduleConsumerConstants.gameListPullInitDelay, fixedRate = ScheduleConsumerConstants.proxyPullRate)
    public void userLevelUpdateSchedule() {
        try {
            userLevelAssemService.batchUpdateLevel(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时更新会员信息
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.proxyPullRate)
//    @Scheduled(cron = "0 */2 * * * ?")
    public void userInfoSyncSchedule() {
        try {
            userInfoAssemService.batchUserInfoSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
