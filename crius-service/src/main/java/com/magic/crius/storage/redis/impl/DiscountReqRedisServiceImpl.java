package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.DiscountReqRedisService;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:35
 */
@Service
public class DiscountReqRedisServiceImpl implements DiscountReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    @Override
    public boolean save(DiscountReq discountReq) {
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_DISCOUNT.key(DateUtil.formatDateTime(new Date(discountReq.getConsumerTime()), DateUtil.format_yyyyMMddHH));
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            Long result = jedis.lpush(key, JSON.toJSONString(discountReq));
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("DiscountReq save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            ApiLogger.error("DiscountReq save error, key : "+ key, e);
        }
        return false;
    }

    @Override
    public List<DiscountReq> batchPop(Date date) {
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_DISCOUNT.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            List<DiscountReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, DiscountReq.class));
                } else {
                    reqStr = jedis.rpop(key);
                    if (StringUtils.isNotBlank(reqStr)) {
                        list.add(JSON.parseObject(reqStr, DiscountReq.class));
                    } else {
                        break;
                    }
                }
            }
            ApiLogger.debug("DiscountReq batchPop , key : "+key + ", size : "+list.size());
            return list;
        } catch (Exception e) {
            ApiLogger.error("DiscountReq batchPop error, key : "+ key, e);
        } finally {
            criusJedisFactory.close(jedis);
        }
        return null;
    }
}
