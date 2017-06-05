package com.magic.crius.assemble;

import com.magic.crius.po.OwnerPreferentialSummary;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:35
 * 优惠汇总
 */
public interface OwnerPreferentialSummaryAssemService {

    /**
     * 批量添加优惠汇总
     * @param ownerPreferentialSummaries
     */
    void batchSave(Collection<OwnerPreferentialSummary> ownerPreferentialSummaries);
}
