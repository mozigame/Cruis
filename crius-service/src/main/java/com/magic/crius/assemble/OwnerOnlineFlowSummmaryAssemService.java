package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.po.OwnerOnlineFlowSummmary;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 17:17
 * 线上入款汇总
 */
public interface OwnerOnlineFlowSummmaryAssemService {

    /**
     * 批量添加线上入款汇总
     * @param ownerOnlineFlowSummmaries
     */
    void batchSave(Collection<OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaries);
}
