package com.magic.crius.storage.db;

import com.magic.api.commons.model.Page;
import com.magic.crius.po.UserInfo;

import java.util.List;

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

    boolean updateLevel(Long userId, Long level);
    
    int getSummaryNum(UserInfo record);
    
    int getProxyNum(UserInfo record);
    
    public int batchInsert(List<UserInfo> list);
    
    public int updateByPrimaryKey(UserInfo userInfo);
    
    /**
     * 根据userId查用户
     * @param userIdList
     * @param proxyIdList
     * @return
     */
    List<UserInfo> findUserInfoList(List<Long> userIdList,
    		List<Long> proxyIdList);

    /**
     * 根据层级获取用户列表
     * @param userInfo
     * @return
     */
    List<UserInfo> findUserLevel(UserInfo userInfo, Page page);
}
