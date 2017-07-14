package com.magic.crius.storage.db;

import com.magic.crius.po.BillInfo;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:14
 * 账单汇总表
 */
public interface BillInfoDbService {

    boolean save(BillInfo billInfo);

    boolean isExistBillInfo(BillInfo billInfo);
}
