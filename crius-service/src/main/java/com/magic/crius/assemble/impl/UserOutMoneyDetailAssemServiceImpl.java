package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.service.UserOutMoneyDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:45
 * 会员出款明细
 */
@Service
public class UserOutMoneyDetailAssemServiceImpl implements UserOutMoneyDetailAssemService {

    @Resource
    private UserOutMoneyDetailService userOutMoneyDetailService;

    @Override
    public void batchSave(List<UserOutMoneyDetail> details) {
        userOutMoneyDetailService.batchInsert(details);
    }
}
