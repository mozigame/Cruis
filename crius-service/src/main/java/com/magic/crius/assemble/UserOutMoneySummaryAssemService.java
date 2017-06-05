package com.magic.crius.assemble;

import com.magic.crius.po.UserOutMoneySummary;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:42
 * 会员出款
 */
public interface UserOutMoneySummaryAssemService {

    /**
     * 批量添加会员出款明细
     * @param userOutMoneySummaries
     */
    void batchSave(Collection<UserOutMoneySummary> userOutMoneySummaries);
}
