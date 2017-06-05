package com.magic.crius.service;

import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:37
 */
public interface PreCmpChargeReqService {

    /**
     * 保存原始数据到mongo和缓存
     * @param preCmpChargeReq
     * @return
     */
    boolean savePreCmpCharge(PreCmpChargeReq preCmpChargeReq);

    /**
     * @param reqId
     * @return
     */
    PreCmpChargeReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<PreCmpChargeReq> batchPopRedis(Date date);

}
