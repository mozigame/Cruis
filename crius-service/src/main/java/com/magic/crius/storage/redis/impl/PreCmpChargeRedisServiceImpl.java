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
import java.util.ArrayList;
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
    public List<PreCmpChargeReq> batchPop(Date date) {
        Jedis jedis = criusJedisFactory.getInstance();
        String key = RedisConstants.CLEAR_PREFIX.PLUTUS_CMP_CHARGE.key(DateUtil.formatDateTime(date, "yyMMddHH"));
        List<PreCmpChargeReq> list = new ArrayList<>();
        for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
            String reqStr = jedis.rpop(key);
            if (StringUtils.isNotBlank(reqStr)) {
                list.add(JSON.parseObject(reqStr, PreCmpChargeReq.class));
            } else {
                break;
            }
        }
        return list;
    }


}
