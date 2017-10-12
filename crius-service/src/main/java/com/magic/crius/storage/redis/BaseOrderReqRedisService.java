package com.magic.crius.storage.redis;

import com.magic.crius.vo.BaseOrderReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:22
 */
public interface BaseOrderReqRedisService{


    /**
     * @param req
     * @return
     */
    boolean save(BaseOrderReq req);

    /**
     * @param date
     * @return
     */
    List<BaseOrderReq> batchPop(Date date);


}
