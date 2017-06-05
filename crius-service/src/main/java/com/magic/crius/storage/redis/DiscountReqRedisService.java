package com.magic.crius.storage.redis;

import com.magic.crius.vo.DiscountReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:34
 * 优惠赠送
 */
public interface DiscountReqRedisService {

    /**
     * 保存用户充值成功信息
     * @param discountReq
     * @return
     */
    boolean save(DiscountReq discountReq);

    /**
     * 批量获取用户充值成功信息
     * @param date
     * @return
     */
    List<DiscountReq> batchPop(Date date);
}
