package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerOperateOutDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 人工出款详情
 */
public interface OwnerOperateOutDetailMapper {

    /**
     * @param record
     * @return
     */
    int insert(OwnerOperateOutDetail record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param operateOutType
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("operateOutType") Integer operateOutType, @Param("pdate") Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOperateOutDetail summmary);
}