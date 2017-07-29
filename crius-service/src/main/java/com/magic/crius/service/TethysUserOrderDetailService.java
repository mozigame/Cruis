package com.magic.crius.service;

import com.magic.crius.po.UserOrderDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:40
 */
public interface TethysUserOrderDetailService {


    boolean batchSave(List<UserOrderDetail> userOrderDetails, List<Long> userIds);

    /**
     * 更新派彩状态
     * @param orderDetail
     * @return
     */
    boolean updatePaid(UserOrderDetail orderDetail);

    /**
     * 获取未派彩或者未插入的订单Id列表
     * @return
     */
    List<Long> findNoPaidIds(Collection<UserOrderDetail> orderDetails);
}
