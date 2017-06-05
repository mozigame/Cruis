package com.magic.crius.service;

import com.magic.crius.vo.PreWithdrawReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:21
 */
public interface PreWithdrawReqService {

    /**
     * 保存原始数据到mongo和缓存
     * @param preWithdrawReq
     * @return
     */
    boolean save(PreWithdrawReq preWithdrawReq);

    /**
     * @param reqId
     * @return
     */
    PreWithdrawReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<PreWithdrawReq> batchPopRedis(Date date);
}
