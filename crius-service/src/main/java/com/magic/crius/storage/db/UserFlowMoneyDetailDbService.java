package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.po.UserFlowMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 18:49
 * 用户入款明细
 */
public interface UserFlowMoneyDetailDbService {

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
