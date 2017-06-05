package com.magic.crius.storage.redis;

import com.magic.crius.vo.OperateChargeReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:26
 */
public interface OperateChargeReqRedisService {

    /**
     * 保存人工充值信息
     * @param operateChargeReq
     * @return
     */
    boolean save(OperateChargeReq operateChargeReq);

    /**
     * 批量获取人工充值信息
     * @param date
     * @return
     */
    List<OperateChargeReq> batchPop(Date date);
}
