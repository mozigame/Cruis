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
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            Long result = jedis.incr(key);
            return result == null ? 1 : result.intValue();
        } catch (Exception e) {
            ApiLogger.error("getNoProcPage error, key : " + key, e);
        }finally {
            criusJedisFactory.close(jedis);
        }
        return 1;
    }

    @Override
    public boolean getScheduleSwitch() {
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            String result = jedis.get(RedisConstants.SCHEDULE_SWITCH);
            if (StringUtils.isNotBlank(result)) {
                ApiLogger.debug("getScheduleSwitch success, result : " + result);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            ApiLogger.error("getScheduleSwitch error" , e);
        } finally {
            criusJedisFactory.close(jedis);
        }
        return true;
    }

    @Override
    public void setScheduleSwitch() {
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            jedis.incr(RedisConstants.SCHEDULE_SWITCH);
            ApiLogger.info("setScheduleSwitch success");
        } catch (Exception e) {
            ApiLogger.error("setScheduleSwitch error" , e);
        }finally {
            criusJedisFactory.close(jedis);
        }
    }

    @Override
    public void delScheduleSwitch() {
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            Long result = jedis.del(RedisConstants.SCHEDULE_SWITCH);
            if (result == null || result == 0) {
                ApiLogger.warn("delScheduleSwitch failed");
                jedis.del(RedisConstants.SCHEDULE_SWITCH);
            } else {
                ApiLogger.info("delScheduleSwitch success");
            }
        } catch (Exception e) {
            ApiLogger.error("getScheduleSwitch error" , e);
        }finally {
            criusJedisFactory.close(jedis);
        }
    }
}
