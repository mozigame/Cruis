package com.magic.crius.storage.db;

import com.magic.crius.po.UserPreferentialSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:24
 * 会员优惠汇总
 */
public interface UserPreferentialSummaryDbService {

    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(UserPreferentialSummary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<UserPreferentialSummary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(UserPreferentialSummary summmary);

    /**
     *
     * @return
     */
    List<UserPreferentialSummary> findByUserIds(Collection<Long> userIds, Integer pdate);


}
