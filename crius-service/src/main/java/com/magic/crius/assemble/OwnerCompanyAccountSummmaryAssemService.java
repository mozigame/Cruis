package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 公司账目汇总
 */
public interface OwnerCompanyAccountSummmaryAssemService {


    /**
     * @param ownerCompanyAccountSummmaries
     */
    void batchSave(List<OwnerCompanyAccountSummmary> ownerCompanyAccountSummmaries);
}