package com.magic.crius.storage.redis;

import com.magic.crius.vo.OnlChargeReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:15
 */
public interface OnlChargeRedisService {

    /**
     * 保存用户充值成功信息
     * @param onlChargeReq
     * @return
     */
    boolean save(OnlChargeReq onlChargeReq);

    /**
     * 批量获取用户充值成功信息
     * @param date
     * @return
     */
    List<OnlChargeReq> batchPop(Date date);
}
