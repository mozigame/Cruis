package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerBillSummary2cost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerBillSummary2costMapper {

    int insert(OwnerBillSummary2cost record);

    int batchInsert(@Param("list") List<OwnerBillSummary2cost> costs);
}