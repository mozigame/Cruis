package com.magic.crius.scheduled;

import com.magic.crius.assemble.PreCmpChargeAssemService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.*;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
@Service
public class CriusScheduler {

    private static  final int fixRate = 1000 * 60;

    @Resource
    private PreCmpChargeAssemService preCmpChargeAssemService;


    /**
     *
     */
    @Scheduled(fixedRate = fixRate)
    public void demoSchedule() {
        while (preCmpChargeAssemService.convertData(new Date())) {

        }
    }
}
