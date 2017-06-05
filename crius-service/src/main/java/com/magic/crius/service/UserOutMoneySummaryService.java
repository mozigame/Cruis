package com.magic.crius.service;

import com.magic.crius.po.UserOutMoneySummary;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:26
 */
public interface UserOutMoneySummaryService {

    /**
     * 新增
     * @param summmary
     * @return
     */
    boolean save(UserOutMoneySummary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(UserOutMoneySummary summmary);

    /**
     * 检查是否已经存在数据
     * @param ownerId
     * @param userId
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Long userId, Integer pdate);
}
