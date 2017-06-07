package com.magic.crius.service;

import com.magic.crius.po.PrizeDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:12
 * 彩金明细
 */
public interface PrizeDetailService {

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
