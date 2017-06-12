package com.magic.crius.service;

import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Collection;
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
    List<PreCmpChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取一段时间内入库失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PreCmpChargeReq> getSaveFailed(Long startTime, Long endTime);


    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<PreCmpChargeReq> reqs);

}
