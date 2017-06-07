package com.magic.crius.storage.db;

import com.magic.crius.po.UserAccountSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:18
 * 会员账号汇总
 */
public interface UserAccountSummaryDbService {

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
     *
     * @return
     */
    List<UserAccountSummary> findByOwnerIds(Collection<Long> userIds, Integer pdate);
}
