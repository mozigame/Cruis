package com.magic.crius.storage.db;

import com.magic.crius.po.UserOrderDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:38
 */
public interface TethysUserOrderDetailDbService {

    boolean batchSave(List<UserOrderDetail> userOrderDetails, List<Long> userIds);

    boolean save(UserOrderDetail detail);


    /**
     * 更新派彩状态
     * @param orderDetail
     * @return
     */
    boolean updatePaid(UserOrderDetail orderDetail);

    /**
     * 根据订单号查询订单
     * @param detail
     * @return
     */
    List<UserOrderDetail> findByOrderId(UserOrderDetail detail);
}
