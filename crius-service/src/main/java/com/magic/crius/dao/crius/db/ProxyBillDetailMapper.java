package com.magic.crius.dao.crius.db;

import com.magic.crius.po.ProxyBillDetail;
import org.springframework.stereotype.Component;

@Component
public interface ProxyBillDetailMapper {

    int insert(ProxyBillDetail record);

}