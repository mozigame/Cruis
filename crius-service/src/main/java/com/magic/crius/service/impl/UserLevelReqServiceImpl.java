package com.magic.crius.service.impl;

import com.magic.crius.service.UserLevelReqService;
import com.magic.crius.storage.mongo.UserLevelReqMongoService;
import com.magic.crius.vo.UserLevelReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:18
 */
@Service
public class UserLevelReqServiceImpl implements UserLevelReqService {

    @Resource
    private UserLevelReqMongoService userLevelReqMongoService;

    @Override
    public boolean updateLevel(UserLevelReq userLevelReq) {
        return userLevelReqMongoService.updateLevel(userLevelReq);
    }
}
