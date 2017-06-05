package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OwnerOperateFlowSummmaryMapper {

    /**
     * 添加人工入款汇总
     * @param record
     * @return
     */
    int insert(OwnerOperateFlowSummmary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param operateFlowType
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("operateFlowType") Integer operateFlowType, @Param("pdate") Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOperateFlowSummmary summmary);
}