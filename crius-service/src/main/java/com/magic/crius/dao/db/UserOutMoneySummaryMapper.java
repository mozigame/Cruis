package com.magic.crius.dao.db;

import com.magic.crius.po.UserOutMoneySummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 会员出款明细
 */
@Component
public interface UserOutMoneySummaryMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(UserOutMoneySummary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(UserOutMoneySummary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<UserOutMoneySummary> summmaries);

    /**
     * @return
     */
    List<UserOutMoneySummary> findByUserIds(@Param("list") Collection<Long> userIds, @Param("pdate") Integer pdate);
}