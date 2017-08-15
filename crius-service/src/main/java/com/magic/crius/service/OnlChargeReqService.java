package com.magic.crius.service;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
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
    List<OnlChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OnlChargeReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<OnlChargeReq> reqs);
}
