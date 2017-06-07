package com.magic.crius.service;

import com.magic.crius.vo.CashbackReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:44
 * 返水
 */
public interface CashbackReqService {

    /**
     * @param req
     * @return
     */
    boolean save(CashbackReq req);

    /**
     * @param reqId
     * @return
     */
    CashbackReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的返水信息
     * @param date
     * @return
     */
    List<CashbackReq> batchPopRedis(Date date);
}
