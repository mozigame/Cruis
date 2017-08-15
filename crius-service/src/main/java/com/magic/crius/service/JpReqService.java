package com.magic.crius.service;

import com.magic.crius.vo.JpReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:44
 * 彩金明细
 */
public interface JpReqService {

    /**
     * @param req
     * @return
     */
    boolean save(JpReq req);

    /**
     * @param reqId
     * @return
     */
    JpReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的彩金明细信息
     * @param date
     * @return
     */
    List<JpReq> batchPopRedis(Date date);


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
    List<JpReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<JpReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<JpReq> reqs);
}
