package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerBillSummary2game;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerBillSummary2gameMapper {

    int insert(OwnerBillSummary2game record);

    /**
     * 批量添加业主月游戏账单汇总
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OwnerBillSummary2game> list);
}