package com.magic.crius.service;

import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:45
 * 人工提现
 */
public interface OperateWithDrawReqService {


    /**
     * @param req
     * @return
     */
    boolean save(OperateWithDrawReq req);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<OperateWithDrawReq> reqs);

    /**
     * @param reqId
     * @return
     */
    OperateWithDrawReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的人工提现信息
     * @param date
     * @return
     */
    List<OperateWithDrawReq> batchPopRedis(Date date);

    /**
     * 获取操作成功的ID
     * @param
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取未处理的数据
     * @return
     */
    List<OperateWithDrawReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OperateWithDrawReq> getSaveFailed(Long startTime, Long endTime);
}
