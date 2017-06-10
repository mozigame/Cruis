package com.magic.crius.service;

import com.magic.crius.po.UserPreferentialDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:29
 * 会员优惠汇总
 */
public interface UserPreferentialDetailService {
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
     * @param details
     * @return
     */
    boolean batchInsert(Collection<UserPreferentialDetail> details);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateDetail(UserPreferentialDetail summmary);

    /**
     *
     * @return
     */
    List<UserPreferentialDetail> findByUserIds(Collection<Long> userIds, Integer pdate);
}
