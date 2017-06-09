package com.magic.crius.storage.redis;

import com.magic.crius.vo.LotteryReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:36
 * 游戏注单
 */
public interface LotteryReqRedisService {

    /**
     * @param req
     * @return
     */
    boolean save(LotteryReq req);

    /**
     * @param date
     * @return
     */
    List<LotteryReq> batchPop(Date date);
}
