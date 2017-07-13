package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.OperateWithDrawReqRedisService;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:43
 */
@Service
public class OperateWithDrawReqRedisServiceImpl implements OperateWithDrawReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Override
    public boolean save(OperateWithDrawReq req) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            Date date = new Date(req.getProduceTime());
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_WITHDRAW.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
            String value = JSON.toJSONString(req);
            Long result = jedis.lpush(key, value);
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            ApiLogger.debug("OperateWithDrawReq save , key : "+key);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OperateWithDrawReq> batchPop(Date date) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_OPR_WITHDRAW.key(DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHH));
            ApiLogger.debug("key :"+key);
            List<OperateWithDrawReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                ApiLogger.debug("reqStr :"+reqStr);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, OperateWithDrawReq.class));
                } else {
                    break;
                }
            }
            ApiLogger.debug("OperateWithDrawReq batchPop , key : "+key+ ", size : "+list.size());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
