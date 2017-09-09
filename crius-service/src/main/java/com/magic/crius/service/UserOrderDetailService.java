package com.magic.crius.service;

import com.magic.crius.po.UserOrderDetail;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:29
 * 注单详情
 */
public interface UserOrderDetailService {

    boolean batchSave(List<UserOrderDetail> details);

    boolean save(UserOrderDetail detail);

    /**
     * 修改派彩状态
     * @param detail
     * @return
     */
    boolean updatePaid(UserOrderDetail detail);

    /**
     * 新增会员更改派彩失败的注单
     * @param detail
     * @return
     */
    boolean saveUpdateFailed(UserOrderDetail detail);


    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<UserOrderDetail> batchPopRedis(Date date);

    /**
     * 根据订单号查询订单
     * @return
     */
    List<UserOrderDetail> findByOrderId(UserOrderDetail detail);


    /**
     * 根据订单号查询订单
     * @return
     */
    Map<Long, UserOrderDetail> findByOrderIds(List<UserOrderDetail> details);
}
