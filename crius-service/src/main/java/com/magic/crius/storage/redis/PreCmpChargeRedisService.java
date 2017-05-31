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


    /**
     * 保存公司入款信息
     * @param preCmpChargeReq
     * @return
     */
    boolean save(PreCmpChargeReq preCmpChargeReq);

    /**
     * 批量获取公司入款信息
     * @param date
     * @return
     */
    List<PreCmpChargeReq> batchPop(Date date);
}
