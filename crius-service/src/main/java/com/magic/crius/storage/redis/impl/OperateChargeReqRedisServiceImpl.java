package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.OperateChargeReqRedisService;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:27
 */
@Service
public class OperateChargeReqRedisServiceImpl implements OperateChargeReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean save(OperateChargeReq operateChargeReq) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_CHARGE.key(DateUtil.formatDateTime(new Date(operateChargeReq.getProduceTime()), DateUtil.format_yyyyMMddHH));
            Long result = jedis.lpush(key, JSON.toJSONString(operateChargeReq));
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("OperateChargeReq save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OperateChargeReq> batchPop(Date date) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_CHARGE.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
            List<OperateChargeReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, OperateChargeReq.class));
                } else {
                    break;
                }
            }
            ApiLogger.debug("OperateChargeReq batchPop , key : "+key);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
