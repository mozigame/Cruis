package com.magic.crius.service;

import com.magic.crius.po.UserOutMoneyDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:42
 * 会员出款明细
 */
public interface UserOutMoneyDetailService {

    boolean save(UserOutMoneyDetail record);

    boolean batchInsert(List<UserOutMoneyDetail> details);
}
