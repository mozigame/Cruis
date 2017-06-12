package com.magic.crius.service;

import com.magic.crius.vo.BaseOrderReq;

import java.util.Collection;
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

    /**
     * 批量添加处理成功的数据ID
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<BaseOrderReq> reqs);

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
    List<BaseOrderReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime);
}
