package com.magic.crius.service;

import com.magic.crius.po.ProxyPreferentialSummary;
import com.magic.crius.po.UserPreferentialSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:29
 * 会员优惠汇总
 */
public interface UserPreferentialSummaryService {
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
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<UserPreferentialSummary> findByOwnerIds(Collection<Long> userIds, Integer pdate);
}
