package com.magic.crius.storage.redis;

import com.magic.crius.vo.EGameReq;
import com.magic.crius.vo.LotteryReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:14
 */
public interface EGameReqRedisService {


    /**
     * @param req
     * @return
     */
    boolean save(EGameReq req);

    /**
     * @param date
     * @return
     */
    List<EGameReq> batchPop(Date date);
}
