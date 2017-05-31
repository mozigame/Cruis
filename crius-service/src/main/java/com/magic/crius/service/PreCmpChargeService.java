package com.magic.crius.service;

import com.magic.crius.vo.PreCmpChargeReq;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:37
 */
public interface PreCmpChargeService {

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

}
