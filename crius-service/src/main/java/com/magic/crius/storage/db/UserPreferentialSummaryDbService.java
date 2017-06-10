package com.magic.crius.storage.db;

import com.magic.crius.po.UserPreferentialDetail;

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
    boolean insert(UserPreferentialDetail summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<UserPreferentialDetail> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(UserPreferentialDetail summmary);

    /**
     *
     * @return
     */
    List<UserPreferentialDetail> findByUserIds(Collection<Long> userIds, Integer pdate);


}
