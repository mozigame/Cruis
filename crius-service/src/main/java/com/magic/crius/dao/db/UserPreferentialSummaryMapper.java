package com.magic.crius.dao.db;

import com.magic.crius.po.UserPreferentialSummary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 会员优惠汇总
 */
@Component
public interface UserPreferentialSummaryMapper {


    /**
     * @param summmary
     * @return
     */
    int insert(UserPreferentialSummary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(UserPreferentialSummary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<UserPreferentialSummary> summmaries);

    /**
     * @return
     */
    List<UserPreferentialSummary> findByUserIds(@Param("list") Collection<Long> userIds, @Param("pdate") Integer pdate);

}