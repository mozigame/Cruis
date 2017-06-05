package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;

import java.util.Collection;

/**
 * 公司账目汇总
 */
public interface OwnerCompanyAccountSummmaryAssemService {


    /**
     * 批量添加公司入款明细
     * @param ownerCompanyAccountSummmaries
     */
    void batchSave(Collection<OwnerCompanyAccountSummmary> ownerCompanyAccountSummmaries);
}