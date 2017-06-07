package com.magic.crius.service;

import com.magic.crius.po.UserOutMoneySummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:26
 * 会员出款汇总
 */
public interface UserOutMoneySummaryService {

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
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<UserOutMoneySummary> findByOwnerIds(Collection<Long> userIds, Integer pdate);
}
