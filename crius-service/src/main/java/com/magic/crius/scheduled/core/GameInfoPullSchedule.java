package com.magic.crius.scheduled.core;

import com.magic.crius.assemble.GameInfoAssemService;
import com.magic.crius.constants.ScheduleConsumerConstants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:26
 */
@Component
public class GameInfoPullSchedule {


    /*游戏列表*/
    @Resource
    private GameInfoAssemService gameInfoAssemService;

    /**
     * 定时拉取游戏列表
     */
    @Scheduled(initialDelay = ScheduleConsumerConstants.gameListPullInitDelay, fixedRate = ScheduleConsumerConstants.gameListPullRate)
    public void gameInfoPullSchedule() {
        gameInfoAssemService.init();
    }

}
