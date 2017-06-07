package com.magic.crius.service.impl;

import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.service.UserOutMoneyDetailService;
import com.magic.crius.storage.db.UserOutMoneyDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:42
 */
@Service
public class UserOutMoneyDetailServiceImpl implements UserOutMoneyDetailService {

    @Resource
    private UserOutMoneyDetailDbService userOutMoneyDetailDbService;

    @Override
    public boolean save(UserOutMoneyDetail record) {
        return userOutMoneyDetailDbService.save(record);
    }

    @Override
    public boolean batchInsert(List<UserOutMoneyDetail> details) {
        return userOutMoneyDetailDbService.batchInsert(details);
    }
}
