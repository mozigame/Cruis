package com.magic.crius.dao.crius.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.magic.crius.po.UserInfo;

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
    
    List<UserInfo> findUserInfoList(@Param("userIdList") List<Long> userIdList,
    		@Param("proxyIdList") List<Long>proxyIdList); 

    /**
     * 修改会员层级
     * @param userId
     * @param level
     * @return
     */
    int updateLevel(@Param("userId") Long userId,@Param("userLevel") Long level);
    
    
    int getSummaryNum(UserInfo record);

    
    int getProxyNum(UserInfo record);
    
    
    public int batchInsert(List<UserInfo> list);
    
    public int updateByPrimaryKey(UserInfo userInfo);

   

}