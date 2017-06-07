package com.magic.crius.service;

import com.magic.crius.vo.OperateWithDrawReq;

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
}
