package com.magic.crius.service;

import com.magic.crius.vo.LotteryReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:28
 */
public interface LotteryReqService {


    /**
     * 保存原始数据到mongo和缓存
     * @param req
     * @return
     */
    boolean save(LotteryReq req);

    /**
     * @param reqId
     * @return
     */
    LotteryReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<LotteryReq> batchPopRedis(Date date);
}
