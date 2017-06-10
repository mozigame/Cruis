package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserPreferentialDetail;
import org.apache.ibatis.annotations.Param;
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
    int insert(UserPreferentialDetail summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(UserPreferentialDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<UserPreferentialDetail> summmaries);

    /**
     * @return
     */
    List<UserPreferentialDetail> findByUserIds(@Param("list") Collection<Long> userIds, @Param("pdate") Integer pdate);

}