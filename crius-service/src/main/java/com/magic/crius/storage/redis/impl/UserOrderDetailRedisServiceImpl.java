package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.storage.redis.UserOrderDetailRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 21:32
 * 会员订单
 */
@Service
public class UserOrderDetailRedisServiceImpl implements UserOrderDetailRedisService {


    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean save(UserOrderDetail detail) {
        //todo 不需要时间，所有的
//        String key = RedisConstants.CLEAR_PREFIX.USER_ORDER_DETAIL.key(DateUtil.formatDateTime(new Date(detail.getConsumerTime()), DateUtil.format_yyyyMMddHH));
        String key = RedisConstants.CLEAR_PREFIX.USER_ORDER_DETAIL.key();
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            Long result = jedis.lpush(key, JSON.toJSONString(detail));
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("userOrderDetail save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            ApiLogger.error("userOrderDetail save error, key : "+ key, e);
        } finally {
            criusJedisFactory.close(jedis);
        }
        return false;

    }

    @Override
    public List<UserOrderDetail> batchPop(Date date) {
        //todo 不需要时间，所有的
//        String key = RedisConstants.CLEAR_PREFIX.USER_ORDER_DETAIL.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
        String key = RedisConstants.CLEAR_PREFIX.USER_ORDER_DETAIL.key();
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            List<UserOrderDetail> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, UserOrderDetail.class));
                } else {
                    break;
                }
            }
            ApiLogger.debug("userOrderDetail batchPop , key : "+key+ ", size : "+list.size());
            return list;
        } catch (Exception e) {
            ApiLogger.error("userOrderDetail batchPop error, key : "+ key, e);
        } finally {
            criusJedisFactory.close(jedis);
        }
        return null;
    }
}
