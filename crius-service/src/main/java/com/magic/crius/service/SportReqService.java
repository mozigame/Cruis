package com.magic.crius.service;

import com.magic.crius.vo.SportReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:28
 */
public interface SportReqService {

    /**
     * 保存原始数据到mongo和缓存
     * @param req
     * @return
     */
    boolean save(SportReq req);

    /**
     * @param reqId
     * @return
     */
    SportReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<SportReq> batchPopRedis(Date date);
}
