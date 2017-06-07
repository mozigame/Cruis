package com.magic.crius.assemble;

import com.magic.crius.po.UserOutMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:43
 * 会员出款明细
 */
public interface UserOutMoneyDetailAssemService {

    /**
     * @param details
     * @return
     */
    void batchSave(List<UserOutMoneyDetail> details);
}
