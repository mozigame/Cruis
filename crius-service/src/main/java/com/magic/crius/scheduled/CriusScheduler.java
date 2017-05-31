package com.magic.crius.scheduled;

import com.magic.crius.assemble.PreCmpChargeAssemService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
@Service
public class CriusScheduler {



    private PreCmpChargeAssemService preCmpChargeAssembleService;


    @Scheduled(cron = "")
    public void demoSchedule() {

    }

}
