package com.magic.crius.dao.db;

import com.magic.crius.po.UserOutMoneySummary;
import org.apache.ibatis.annotations.Param;

public interface UserOutMoneySummaryMapper {
    /**
     * 添加会员出款汇总
     * @param record
     * @return
     */
    int insert(UserOutMoneySummary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param userId
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("userId") Long userId, @Param("pdate") Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(UserOutMoneySummary summmary);
}