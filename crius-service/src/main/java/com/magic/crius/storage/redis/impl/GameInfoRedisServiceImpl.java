package com.magic.crius.storage.redis.impl;

import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.GameInfoRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.GAME_INFO_LOCK;
            Long result = jedis.incr(key);
            jedis.expire(key, RedisConstants.GAME_INFO_LOCK_EXPIRE);
            return result != null && result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getLock() {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String result = jedis.get(RedisConstants.GAME_INFO_LOCK);
            return StringUtils.isNotBlank(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
