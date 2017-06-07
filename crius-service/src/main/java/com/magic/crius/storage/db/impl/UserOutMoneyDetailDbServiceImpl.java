package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserOutMoneyDetailMapper;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.storage.db.UserOutMoneyDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:40
 */
@Service
public class UserOutMoneyDetailDbServiceImpl implements UserOutMoneyDetailDbService {


    @Resource
    private UserOutMoneyDetailMapper userOutMoneyDetailMapper;

    @Override
    public boolean save(UserOutMoneyDetail record) {
        return userOutMoneyDetailMapper.insert(record) > 0;
    }

    @Override
    public boolean batchInsert(List<UserOutMoneyDetail> details) {
        return userOutMoneyDetailMapper.batchInsert(details) > 0;
    }
}
