package com.magic.crius.service;

import com.magic.crius.po.UserAccountSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:19
 * 会员账号汇总
 */
public interface UserAccountSummaryService {


    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(UserAccountSummary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<UserAccountSummary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(UserAccountSummary summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<UserAccountSummary> findByUserIds(Collection<Long> userIds, Integer pdate);
}
