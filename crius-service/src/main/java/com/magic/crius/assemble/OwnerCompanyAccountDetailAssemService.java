package com.magic.crius.assemble;

import com.magic.crius.po.OwnerCompanyAccountDetail;

import java.util.List;

/**
 * 公司账目汇总
 */
public interface OwnerCompanyAccountDetailAssemService {


    /**
     * @param ownerCompanyAccountSummmaries
     */
    void batchSave(List<OwnerCompanyAccountDetail> ownerCompanyAccountSummmaries);
}