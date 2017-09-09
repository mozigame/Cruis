package com.magic.crius.storage.db;

import com.magic.crius.po.UserOrderDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:28
 * 注单详情
 */
public interface UserOrderDetailDbService {

    boolean batchSave(List<UserOrderDetail> details);

    boolean save(UserOrderDetail detail);


    /**
     * 修改派彩状态
     * @param detail
     * @return
     */
    boolean updatePaid(UserOrderDetail detail);

    /**
     * 根据订单号查询订单
     * @param detail
     * @return
     */
    List<UserOrderDetail> findByOrderId(UserOrderDetail detail);

    /**
     * 根据订单号查询订单
     * @return
     */
    List<UserOrderDetail> findByOrderIds(List<UserOrderDetail> details);
}
