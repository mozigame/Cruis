package com.magic.crius.service;

import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.UserFlowMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:05
 * 会员入款明细
 */
public interface UserFlowMoneyDetailService  {

    /**
     * @param detail
     * @return
     */
    boolean save(UserFlowMoneyDetail detail);

    /**
     * @param details
     * @return
     */
    boolean batchSave(List<UserFlowMoneyDetail> details);
}
