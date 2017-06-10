package com.magic.crius.dao.tethys.db;

import com.magic.crius.po.UserOrderDetail;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("tethysUserOrderDetailMapper")
public interface UserOrderDetailMapper {

    int insert(UserOrderDetail record);

    int batchInsert(Collection<UserOrderDetail> userOrderDetails);
}