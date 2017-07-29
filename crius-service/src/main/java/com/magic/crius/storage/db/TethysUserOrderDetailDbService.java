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

    /**
     * 获取未派彩或者未插入的订单Id列表
     * @param userIds
     * @return
     */
    List<Long> findNoPaidIds(List<UserOrderDetail> orderDetails,List<Long> userIds);
}
