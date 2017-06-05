package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOnlineFlowSummmary;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:59
 */
public interface OwnerOnlineFlowSummmaryDbService {

    /**
     * 新增
     * @param summmary
     * @return
     */
    boolean save(OwnerOnlineFlowSummmary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerOnlineFlowSummmary summmary);

    /**
     * 检查是否已经存在数据
     * @param ownerId
     * @param merchantCode
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Long merchantCode, Integer pdate);
}
