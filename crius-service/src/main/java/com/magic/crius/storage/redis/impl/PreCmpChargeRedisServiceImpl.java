package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.PreCmpChargeRedisService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
@Service("preCmpChargeRedisService")
public class PreCmpChargeRedisServiceImpl implements PreCmpChargeRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean save(PreCmpChargeReq preCmpChargeReq) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.key(DateUtil.formatDateTime(new Date(preCmpChargeReq.getProduceTime()), "yyMMddHH"));
            Long result = jedis.lpush(key, JSON.toJSONString(preCmpChargeReq));
            jedis.expire(key, RedisConstants.EXPIRE_ONE_DAY);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PreCmpChargeReq getFixDate(Date date) {
        Jedis jedis = criusJedisFactory.getInstance();
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.key(DateUtil.formatDateTime(date, "yyMMddHH"));
        String lockKey = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.lock();

        if (StringUtils.isNotBlank(jedis.get(lockKey))) {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotBlank(jedis.get(lockKey))) {
            return null;
        }
        jedis.incr(lockKey);
        String indexKey = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.index();
        long length = jedis.llen(key);
        Long index = Long.valueOf(jedis.get(indexKey));
        if (index == null) {
            index = 0L;
        }
        //TODO
        long differ = length - index;
        if (differ <= 1000) {
            jedis.setex(indexKey, RedisConstants.EXPIRE_ONE_DAY, length + "");
            jedis.del(lockKey);
            List<String> list = jedis.lrange(key, index, length - 1);
        } else if (differ > 1000 && differ <= 2000) {
            List<String > list = jedis.lrange(key, index, 1000L);
        }
        return null;
    }


}
