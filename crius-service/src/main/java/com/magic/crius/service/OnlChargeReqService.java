package com.magic.crius.service;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:12
 */
public interface OnlChargeReqService {


    /**
     * 保存原始数据到mongo和缓存
     * @param onlChargeReq
     * @return
     */
    boolean save(OnlChargeReq onlChargeReq);

    /**
     * @param reqId
     * @return
     */
    OnlChargeReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<OnlChargeReq> batchPopRedis(Date date);


}
