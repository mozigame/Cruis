package com.magic.crius.service;

import com.magic.crius.po.OwnerPreferentialSummary;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 18:23
 * 优惠汇总
 */
public interface OwnerPreferentialSummaryService {

    /**
     * 添加优惠汇总
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
