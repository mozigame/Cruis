package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyFlowSummmary;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
public interface OwnerCompanyFlowSummmaryDbService {

    /**
     * 添加
     * @param flowSummmary
     * @return
     */
    boolean save(OwnerCompanyFlowSummmary flowSummmary);

    /**
     * 修改
     * @param flowSummmary
     * @return
     */
    boolean updateSummary(OwnerCompanyFlowSummmary flowSummmary);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param accountNum
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Long accountNum, Integer pdate);
}
