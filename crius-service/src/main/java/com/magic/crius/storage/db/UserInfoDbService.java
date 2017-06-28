package com.magic.crius.storage.db;

import com.magic.crius.po.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 11:46
 * 会员基础信息
 */
public interface UserInfoDbService {

    boolean save(UserInfo userInfo);

    UserInfo get(Long userId);

    boolean updateLevel(Long userId, Integer level);
}
