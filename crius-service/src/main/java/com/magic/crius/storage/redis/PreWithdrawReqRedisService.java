package com.magic.crius.storage.redis;

import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.PreWithdrawReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:23
 */
public interface PreWithdrawReqRedisService {

    /**
     * 保存用户提现信息
     * @param preWithdrawReq
     * @return
     */
    boolean save(PreWithdrawReq preWithdrawReq);

    /**
     * 批量获取用户提现信息
     * @param date
     * @return
     */
    List<PreWithdrawReq> batchPop(Date date);
}
