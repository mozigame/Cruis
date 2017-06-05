package com.magic.crius.service;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.OperateChargeReq;

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
}
