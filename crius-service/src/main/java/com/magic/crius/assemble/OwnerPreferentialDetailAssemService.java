package com.magic.crius.assemble;

import com.magic.crius.po.OwnerPreferentialDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:35
 * 优惠汇总
 */
public interface OwnerPreferentialDetailAssemService {

    /**
     * 批量添加优惠汇总
     * @param ownerPreferentialSummaries
     */
    void batchSave(List<OwnerPreferentialDetail> ownerPreferentialSummaries);
}
