package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerPreferentialSummary;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:31
 * 优惠汇总
 */
public interface OwnerPreferentialSummaryDbService {

    /**
     * @param record
     * @return
     */
    boolean save(OwnerPreferentialSummary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param preferentialType
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Integer preferentialType, Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerPreferentialSummary summmary);
}
