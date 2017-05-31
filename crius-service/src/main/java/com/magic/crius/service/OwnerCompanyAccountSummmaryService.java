package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyAccountSummmary;

public interface OwnerCompanyAccountSummmaryService {

    /**
     * 添加
     * @param accountSummmary
     * @return
     */
    boolean save(OwnerCompanyAccountSummmary accountSummmary);

    /**
     * 修改
     * @param accountSummmary
     * @return
     */
    boolean updateSummary(OwnerCompanyAccountSummmary accountSummmary);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param summaryType
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Integer summaryType, Integer pdate);

}