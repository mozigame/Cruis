package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserInfoMapper;
import com.magic.crius.po.UserInfo;
import com.magic.crius.storage.db.UserInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 11:47
 */
@Service
public class UserInfoDbServiceImpl implements UserInfoDbService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean save(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo) > 0;
    }

    @Override
    public UserInfo get(Long userId) {
        return userInfoMapper.get(userId);
    }
}
