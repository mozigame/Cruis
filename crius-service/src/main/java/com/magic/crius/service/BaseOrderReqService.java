package com.magic.crius.service;

import com.magic.crius.vo.BaseOrderReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:24
 * 视讯注单
 */
public interface BaseOrderReqService {


    /**
     * 保存原始数据到mongo和缓存
     * @param req
     * @return
     */
    boolean save(BaseOrderReq req);

    /**
     * @param reqId
     * @return
     */
    BaseOrderReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<BaseOrderReq> batchPopRedis(Date date);
}
