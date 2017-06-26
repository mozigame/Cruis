package com.magic.crius.dao.tethys.db;

import com.magic.api.commons.atlas.core.mybatis.MyBatisDaoImpl;
import com.magic.crius.po.UserOrderDetail;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("tethysUserOrderDetailMapper")
public class UserOrderDetailMapper extends MyBatisDaoImpl<UserOrderDetail, Long> {
}