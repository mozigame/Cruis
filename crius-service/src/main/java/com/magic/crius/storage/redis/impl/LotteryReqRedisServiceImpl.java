package com.magic.crius.storage.redis.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.storage.redis.LotteryReqRedisService;
import com.magic.crius.vo.LotteryReq;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:36
 */
@Service
public class LotteryReqRedisServiceImpl implements LotteryReqRedisService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;



    @Override
    public boolean save(LotteryReq req) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_LOTTERY.key(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyMMddHH"));
            Long result = jedis.lpush(key, JSON.toJSONString(req));
            jedis.expire(key, RedisConstants.EXPIRE_THREE_HOUR);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LotteryReq> batchPop(Date date) {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            String key = RedisConstants.CLEAR_PREFIX.PLUTUS_LOTTERY.key(DateUtil.formatDateTime(date, "yyMMddHH"));
            List<LotteryReq> list = new ArrayList<>();
            for (int i = 0; i < RedisConstants.BATCH_POP_NUM; i++) {
                String reqStr = jedis.rpop(key);
                if (StringUtils.isNotBlank(reqStr)) {
                    list.add(JSON.parseObject(reqStr, LotteryReq.class));
                } else {
                    break;
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
