package com.magic.crius.assemble;

import com.magic.crius.po.PrizeDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:13
 * 彩金明细
 */
public interface PrizeDetailAssemService {

    /**
     * @param details
     * @return
     */
    void batchSave(List<PrizeDetail> details);
}
