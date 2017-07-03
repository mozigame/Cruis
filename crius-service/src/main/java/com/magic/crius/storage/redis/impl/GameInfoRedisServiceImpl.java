package com.magic.crius.storage.redis.impl;

import com.magic.api.commons.codis.JedisFactory;
import com.magic.crius.storage.redis.GameInfoRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/1
 * Time: 18:08
 */
@Service
public class GameInfoRedisServiceImpl implements GameInfoRedisService {


    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean setLock() {

        return false;
    }

    @Override
    public boolean getLock() {
        return false;
    }
}
