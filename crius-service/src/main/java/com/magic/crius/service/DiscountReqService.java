package com.magic.crius.service;

import com.magic.crius.vo.DiscountReq;

import java.util.Collection;
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

    /**
     * 获取操作成功的ID
     * @param
     * @return
     */
    List<Long> getSucIds(Long startTime, Long endTime);

    /**
     * 获取未处理的数据
     * @param startTime
     * @param endTime
     * @param reqIds
     * @return
     */
    List<DiscountReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<DiscountReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<DiscountReq> reqs);
}
