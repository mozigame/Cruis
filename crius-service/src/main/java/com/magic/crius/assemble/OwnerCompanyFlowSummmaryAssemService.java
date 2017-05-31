package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyFlowSummmary;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface OwnerCompanyFlowSummmaryAssemService {


    /**
     * 批量添加公司入款明细
     * @param ownerCompanyFlowSummmaries
     */
    void batchSave(Collection<OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaries);
}