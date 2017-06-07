package com.magic.crius.assemble;

import com.magic.crius.po.UserFlowMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:11
 * 会员入款明细
 */
public interface UserFlowMoneyDetailAssemService {


    /**
     * @param details
     * @return
     */
    boolean batchSave(List<UserFlowMoneyDetail> details);
}
