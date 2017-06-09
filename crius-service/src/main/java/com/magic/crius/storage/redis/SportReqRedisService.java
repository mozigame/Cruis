package com.magic.crius.storage.redis;

import com.magic.crius.vo.LotteryReq;
import com.magic.crius.vo.SportReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:12
 * 体育注单
 */
public interface SportReqRedisService {


    /**
     * @param req
     * @return
     */
    boolean save(SportReq req);

    /**
     * @param date
     * @return
     */
    List<SportReq> batchPop(Date date);
}
