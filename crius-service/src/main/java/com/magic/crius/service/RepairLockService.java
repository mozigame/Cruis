package com.magic.crius.service;

import com.magic.crius.po.RepairLock;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 12:04
 */
public interface RepairLockService {


    boolean save(RepairLock lock);

    /**
     * 获取某个时间点的对象锁
     * @param collectionName
     * @param time
     * @return
     */
    RepairLock getTimeLock(String collectionName, Integer time);

    /**
     * 删除一段时间内的锁
     * @param startTime
     * @param endTime
     * @return
     */
    boolean delTimeLock(Long startTime, Long endTime);
}
