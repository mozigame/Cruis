package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.PreCmpChargeReqRedisService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
@Service("preCmpChargeRedisService")
public class PreCmpChargeReqRedisServiceImpl implements PreCmpChargeReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean save(PreCmpChargeReq preCmpChargeReq) {
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.key(DateUtil.formatDateTime(new Date(preCmpChargeReq.getConsumerTime()), DateUtil.format_yyyyMMddHH));
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            Long result = jedis.lpush(key, JSON.toJSONString(preCmpChargeReq));
            ApiLogger.debug("json result :"+JSON.toJSONString(preCmpChargeReq));
            ApiLogger.debug("result :"+result);
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("PreCmpChargeReq save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            ApiLogger.error("PreCmpChargeReq save error, key : "+ key, e);
        }
        return false;
    }

    @Override
    public List<PreCmpChargeReq> batchPop(Date date) {
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            List<PreCmpChargeReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                ApiLogger.debug("reqStr  : "+reqStr);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, PreCmpChargeReq.class));
                } else {
                    break;
                }
            }
            ApiLogger.debug("PreCmpChargeReq batchPop , key : "+key+ ", size : "+list.size());
            return list;
        } catch (Exception e) {
            ApiLogger.error("PreCmpChargeReq batchPop error, key : "+ key, e);
        }
        return null;
    }

    
    

}
