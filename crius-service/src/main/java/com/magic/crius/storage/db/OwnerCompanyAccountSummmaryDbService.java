package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:54
 */
public interface OwnerCompanyAccountSummmaryDbService {

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
