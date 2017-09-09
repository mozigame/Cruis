package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserOrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 注单详情
 */
@Component("criusUserOrderDetailMapper")
public interface UserOrderDetailMapper {

    int insert(UserOrderDetail record);

    int batchInsert(@Param("list") List<UserOrderDetail> userOrderDetails);


    /**
     * 修改派彩状态
     * @param detail
     * @return
     */
    boolean updatePaidStatus(@Param("param") UserOrderDetail detail);

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
    List<UserOrderDetail> findByOrderIds(@Param("list") List<UserOrderDetail> details);
}