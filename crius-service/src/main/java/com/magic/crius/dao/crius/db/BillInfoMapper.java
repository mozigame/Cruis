package com.magic.crius.dao.crius.db;

import com.magic.crius.po.BillInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BillInfoMapper {

    int insert(BillInfo record);

    int isExistBillInfo(BillInfo record);
}