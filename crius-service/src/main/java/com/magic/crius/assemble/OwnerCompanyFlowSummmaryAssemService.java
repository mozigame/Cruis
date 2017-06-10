package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyFlowDetail;

import java.util.List;

/**
 * 公司入款明细
 */
public interface OwnerCompanyFlowSummmaryAssemService {


    /**
     * 批量添加公司入款明细
     * @param ownerCompanyFlowSummmaries
     */
    void batchSave(List<OwnerCompanyFlowDetail> ownerCompanyFlowSummmaries);
}