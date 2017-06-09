package com.magic.crius.dao.db;

import com.magic.crius.po.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 会员基础信息
 */
@Component
public interface UserInfoMapper {

    int insert(UserInfo record);

    UserInfo get(@Param("userId") Long userId);

}