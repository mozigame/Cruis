package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerBillSummary2game;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:27
 * 业主月游戏账单汇总
 */
public interface OwnerBillSummary2gameDbService {

    boolean save(OwnerBillSummary2game summary2game);
}
