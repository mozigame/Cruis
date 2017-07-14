package com.magic.crius.service;

import com.magic.crius.po.UserInfo;
import com.magic.user.entity.User;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:02
 */
public interface UserInfoService {

    boolean save(UserInfo userInfo);

    UserInfo get(Long userId);

    boolean updateLevel(Long userId, Long level);

    int getSummaryUserNum(UserInfo userInfo);

    int getSummaryProxyNum(UserInfo userInfo);
}
