package com.magic.crius.service.impl;

import com.magic.crius.po.RepairLock;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.storage.mongo.RepairLockMongoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 12:04
 */
@Service
public class RepairLockServiceImpl implements RepairLockService {

    @Resource
    private RepairLockMongoService repairLockMongoService;

    @Override
    public boolean save(RepairLock lock) {
        return repairLockMongoService.save(lock);
    }

    @Override
    public RepairLock getTimeLock(String collectionName, Integer time) {
        return repairLockMongoService.getTimeLock(collectionName, time);
    }

    @Override
    public boolean delTimeLock(Long startTime, Long endTime) {
        return repairLockMongoService.delTimeLock(startTime, endTime);
    }
}
