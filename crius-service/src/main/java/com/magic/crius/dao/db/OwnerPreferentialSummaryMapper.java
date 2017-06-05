package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerPreferentialSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 优惠汇总
 */
@Component
public interface OwnerPreferentialSummaryMapper {

    /**
     * 添加优惠汇总
     * @param summary
     * @return
     */
    int insert(OwnerPreferentialSummary summary);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param preferentialType
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("preferentialType") Integer preferentialType, @Param("pdate") Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerPreferentialSummary summmary);
}