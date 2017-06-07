package com.magic.crius.storage.db;

import com.magic.crius.po.UserOutMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:39
 * 会员出款明细
 */
public interface UserOutMoneyDetailDbService {

    boolean save(UserOutMoneyDetail record);

    boolean batchInsert(List<UserOutMoneyDetail> details);
}
