package com.magic.crius.scheduled.core;

import com.magic.crius.assemble.GameInfoAssemService;
import com.magic.crius.constants.CriusInitConstants;
import com.magic.crius.service.BaseReqService;
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
    @Resource
    private BaseReqService baseReqService;


    /**
     * 定时拉取游戏列表
     */
    @Scheduled(initialDelay = CriusInitConstants.gameListPullInitDelay, fixedRate = CriusInitConstants.gameListPullRate)
    public void gameInfoPullSchedule() {
        //如果没有开启定时任务的开关，不执行
        if (!baseReqService.getScheduleSwitch()) {
            return;
        }
        gameInfoAssemService.init();
    }

}
