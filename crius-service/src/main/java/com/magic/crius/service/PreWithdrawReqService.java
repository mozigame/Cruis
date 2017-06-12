package com.magic.crius.service;

import com.magic.crius.vo.PreCmpChargeReq;
import com.magic.crius.vo.PreWithdrawReq;

import java.util.Collection;
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
    List<PreWithdrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取一段时间内入库失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PreWithdrawReq> getSaveFailed(Long startTime, Long endTime);


    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<PreWithdrawReq> reqs);
}
