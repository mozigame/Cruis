package com.magic.crius.scheduled.core;

import com.magic.crius.assemble.UserInfoAssemService;
import com.magic.crius.assemble.UserLevelAssemService;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.service.BaseReqService;
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
    @Resource
    private BaseReqService baseReqService;


    /**
     * 定时更新会员层级
     */
    @Scheduled(initialDelay = CriusInitConstants.gameListPullInitDelay, fixedRate = CriusInitConstants.proxyPullRate)
    public void userLevelUpdateSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            userLevelAssemService.batchUpdateLevel(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时更新会员信息
     */
    @Scheduled(fixedRate = CriusInitConstants.proxyPullRate)
    public void userInfoSyncSchedule() {
        try {
            //如果没有开启定时任务的开关，不执行
            if (!baseReqService.getScheduleSwitch()) {
                return;
            }
            userInfoAssemService.batchUserInfoSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
