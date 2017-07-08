package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerBillSummary2game;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerBillSummary2gameMapper {

    int insert(OwnerBillSummary2game record);
}