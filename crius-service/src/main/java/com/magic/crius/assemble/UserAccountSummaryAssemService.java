package com.magic.crius.assemble;

import com.magic.crius.po.UserAccountSummary;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:21
 * 会员账号汇总
 */
@Deprecated
public interface UserAccountSummaryAssemService {

    /**
     * 修改充值信息
     * @param summaries
     * @return
     */
    @Deprecated
    boolean updateRecharge(List<UserAccountSummary> summaries);

    /**
     * 修改取款信息
     * @param summaries
     * @return
     */
    @Deprecated
    boolean updateWithdraw(List<UserAccountSummary> summaries);



}
