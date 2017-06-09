package com.magic.crius.service.impl;

import com.magic.crius.po.UserInfo;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.storage.db.UserInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:02
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDbService userInfoDbService;

    @Override
    public boolean save(UserInfo userInfo) {
        return userInfoDbService.save(userInfo);
    }

    @Override
    public UserInfo get(Long userId) {
        return userInfoDbService.get(userId);
    }
}
