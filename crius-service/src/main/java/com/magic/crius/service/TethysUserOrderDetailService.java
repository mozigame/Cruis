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
     * 获取未派彩或者未插入的订单Id列表
     * @param userIds
     * @return
     */
    List<Long> findNoPaidIds(List<UserOrderDetail> orderDetails,List<Long> userIds);
}
