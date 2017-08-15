package com.magic.crius.scheduled.core;

import com.magic.crius.service.RepairLockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:26
 */
@Component
public class RepairLockSchedule {


    @Resource
    private RepairLockService repairLockService;

    /**
     * 定时删除修复数据的锁
     * 每天的凌晨2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void repairLockSchedule() {
        try {
            repairLockService.delTimeLock(0L, System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
