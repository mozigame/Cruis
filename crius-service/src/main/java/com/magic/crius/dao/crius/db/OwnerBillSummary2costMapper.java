package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerBillSummary2cost;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerBillSummary2costMapper {

    int insert(OwnerBillSummary2cost record);
}