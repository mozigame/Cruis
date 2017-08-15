package com.magic.crius.service;

import java.util.List;

import com.magic.api.commons.model.Page;
import com.magic.crius.po.UserInfo;

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
    
    /**
     * 批量插入数据
     * @param list
     * @return
     */
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
