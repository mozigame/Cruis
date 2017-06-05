package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerOnlineFlowSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface OwnerOnlineFlowSummmaryMapper {

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(OwnerOnlineFlowSummmary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param merchantCode
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("merchantCode") Long merchantCode, @Param("pdate") Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOnlineFlowSummmary summmary);

}