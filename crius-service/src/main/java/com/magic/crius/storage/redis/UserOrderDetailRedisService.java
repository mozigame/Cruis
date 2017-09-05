package com.magic.crius.storage.redis;

import com.magic.crius.po.UserOrderDetail;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 21:27
 */
public interface UserOrderDetailRedisService {

    /**
     * 保存修改派彩失败的用户订单
     * @param detail
     * @return
     */
    boolean save(UserOrderDetail detail);

    /**
     * 批量获取修改派彩失败的用户订单
     * @param date
     * @return
     */
    List<UserOrderDetail> batchPop(Date date);
}
