package com.magic.crius.storage.db;

import com.magic.crius.po.UserOutMoneySummary;

import java.util.Collection;
import java.util.List;

/**
 * 会员出款汇总
 */
public interface UserOutMoneySummaryDbService {
    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(UserOutMoneySummary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<UserOutMoneySummary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(UserOutMoneySummary summmary);

    /**
     *
     * @return
     */
    List<UserOutMoneySummary> findByUserIds(Collection<Long> userIds, Integer pdate);
}