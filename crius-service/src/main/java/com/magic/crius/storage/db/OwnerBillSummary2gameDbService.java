package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerBillSummary2game;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:27
 * 业主月游戏账单汇总
 */
public interface OwnerBillSummary2gameDbService {

    boolean save(OwnerBillSummary2game summary2game);

    /**
     * 批量添加业主月游戏账单汇总
     * @param list
     * @return
     */
    boolean batchInsert(List<OwnerBillSummary2game> list);
}
