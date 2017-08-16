package com.magic.crius.storage.redis.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
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

    @Override
    public boolean getScheduleSwitch() {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String result = jedis.get(RedisConstants.SCHEDULE_SWITCH);
            return StringUtils.isNotBlank(result);
        } catch (Exception e) {
            ApiLogger.error("getScheduleSwitch error" , e);
        }
        return true;
    }

    @Override
    public void setScheduleSwitch() {
        try {
            criusJedisFactory.getInstance().incr(RedisConstants.SCHEDULE_SWITCH);
        } catch (Exception e) {
            ApiLogger.error("setScheduleSwitch error" , e);
        }
    }

    @Override
    public void delScheduleSwitch() {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            Long result = jedis.del(RedisConstants.SCHEDULE_SWITCH);
            if (result == null || result == 0) {
                ApiLogger.warn("delScheduleSwitch failed");
                jedis.del(RedisConstants.SCHEDULE_SWITCH);
            }
        } catch (Exception e) {
            ApiLogger.error("getScheduleSwitch error" , e);
        }
    }
}
