package com.magic.crius.storage.redis.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.crius.storage.redis.BaseReqRedisService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/14
 * Time: 17:58
 */
@Service
public class BaseReqRedisServiceImpl implements BaseReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public int getNoProcPage(String key) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            Long result = jedis.incr(key);
            return result == null ? 0 : result.intValue();
        } catch (Exception e) {
            ApiLogger.error("getNoProcPage error, key : " + key, e);
        }
        return 0;
    }
}
