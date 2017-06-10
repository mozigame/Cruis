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
public interface UserPreferentialDetailDbService {

    /**
     * 添加
     *
     * @param detail
     * @return
     */
    boolean insert(UserPreferentialDetail detail);

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
     * @param detail
     * @return
     */
    boolean updateDetail(UserPreferentialDetail detail);

    /**
     *
     * @return
     */
    List<UserPreferentialDetail> findByUserIds(Collection<Long> userIds, Integer pdate);


}
