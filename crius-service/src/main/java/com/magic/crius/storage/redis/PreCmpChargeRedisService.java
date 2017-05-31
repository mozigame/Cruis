package com.magic.crius.storage.redis;

import com.magic.crius.vo.PreCmpChargeReq;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
public interface PreCmpChargeRedisService {


    boolean save(PreCmpChargeReq preCmpChargeReq);


    PreCmpChargeReq getFixDate(Date date);
}
