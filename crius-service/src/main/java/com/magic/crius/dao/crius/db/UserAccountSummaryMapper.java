package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserAccountSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 会员账号汇总
 */
@Component
public interface UserAccountSummaryMapper {


    /**
     * @param summmary
     * @return
     */
    int insert(UserAccountSummary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(UserAccountSummary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<UserAccountSummary> summmaries);

    /**
     * @return
     */
    List<UserAccountSummary> findByUserIds(@Param("list") Collection<Long> userIds, @Param("pdate") Integer pdate);
}