package com.magic.crius.service;

import com.magic.crius.po.OwnerOperateFlowSummmary;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:11
 */
public interface OwnerOperateFlowSummmaryService {

    /**
     * 添加人工入款汇总
     * @param record
     * @return
     */
    boolean save(OwnerOperateFlowSummmary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param operateFlowType
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Integer operateFlowType, Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerOperateFlowSummmary summmary);
}
