package com.magic.crius.dao.db;

import com.magic.crius.po.UserOrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 注单详情
 */
@Component
public interface UserOrderDetailMapper {

    int insert(UserOrderDetail record);

    int batchInsert(@Param("list") List<UserOrderDetail> userOrderDetails);
}