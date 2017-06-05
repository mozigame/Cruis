package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import org.apache.ibatis.annotations.Param;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:08
 */
public interface OwnerOperateFlowSummmaryDbService {

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
