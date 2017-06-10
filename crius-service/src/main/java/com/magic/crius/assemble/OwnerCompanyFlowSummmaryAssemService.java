package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyFlowSummmary;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 公司入款明细
 */
public interface OwnerCompanyFlowSummmaryAssemService {


    /**
     * 批量添加公司入款明细
     * @param ownerCompanyFlowSummmaries
     */
    void batchSave(List<OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaries);
}