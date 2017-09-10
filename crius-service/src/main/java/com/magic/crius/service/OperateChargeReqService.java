package com.magic.crius.service;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:41
 */
public interface OperateChargeReqService {

    /**
     * 保存人工充值信息
     * @param operateChargeReq
     * @return
     */
    boolean save(OperateChargeReq operateChargeReq);

    /**
     * @param reqId
     * @return
     */
    OperateChargeReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的人工充值信息
     * @param date
     * @return
     */
    List<OperateChargeReq> batchPopRedis(Date date);


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
    List<OperateChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OperateChargeReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<OperateChargeReq> reqs);
}
