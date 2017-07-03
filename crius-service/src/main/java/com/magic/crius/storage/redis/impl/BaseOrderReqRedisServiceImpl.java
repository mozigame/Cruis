package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.BaseOrderReqRedisService;
import com.magic.crius.vo.BaseOrderReq;
import com.magic.crius.vo.VGameReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:22
 */
@Service
public class BaseOrderReqRedisServiceImpl implements BaseOrderReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    @Override
    public boolean save(BaseOrderReq req) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_BASE_GAME.key(DateUtil.formatDateTime(new Date(req.getProduceTime()), DateUtil.format_yyyyMMddHH));
            Long result = jedis.lpush(key, JSON.toJSONString(req));
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("BaseOrderReq save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BaseOrderReq> batchPop(Date date) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_BASE_GAME.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
            List<BaseOrderReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, VGameReq.class));
                } else {
                    break;
                }
            }
            ApiLogger.debug("BaseOrderReq batchPop , key : "+key);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
