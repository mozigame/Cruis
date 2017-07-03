package com.magic.crius.storage.redis;

/**
 * User: joey
 * Date: 2017/7/1
 * Time: 18:08
 */
public interface GameInfoRedisService {

    boolean setLock();

    boolean getLock();
}
