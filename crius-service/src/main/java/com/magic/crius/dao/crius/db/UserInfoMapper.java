package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 会员基础信息
 */
@Component
public interface UserInfoMapper {

    /**
     * 添加会员
     * @param record
     * @return
     */
    int insert(UserInfo record);

    /**
     * 获取会员
     * @param userId
     * @return
     */
    UserInfo get(@Param("userId") Long userId);

    /**
     * 修改会员层级
     * @param userId
     * @param level
     * @return
     */
    int updateLevel(@Param("userId") Long userId,@Param("userLevel") Long level);
    
    
    UserInfo getSummaryNum(UserInfo record);
    
    UserInfo getProxyNum(UserInfo record);
   

}