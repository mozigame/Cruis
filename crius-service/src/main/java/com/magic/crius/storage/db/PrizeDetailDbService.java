package com.magic.crius.storage.db;

import com.magic.crius.po.PrizeDetail;
import com.magic.crius.po.UserAccountSummary;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:09
 * 彩金明细
 */
public interface PrizeDetailDbService {

    /**
     * @param detail
     * @return
     */
    boolean save(PrizeDetail detail);


    /**
     * @param details
     * @return
     */
    boolean batchSave(List<PrizeDetail> details);
}
