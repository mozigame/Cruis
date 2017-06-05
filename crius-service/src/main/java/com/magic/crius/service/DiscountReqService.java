package com.magic.crius.service;

import com.magic.crius.vo.DiscountReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:08
 * 优惠赠送
 */
public interface DiscountReqService {

    /**
     * 保存原始数据到mongo和缓存
     * @param discountReq
     * @return
     */
    boolean save(DiscountReq discountReq);

    /**
     * @param reqId
     * @return
     */
    DiscountReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<DiscountReq> batchPopRedis(Date date);
}
