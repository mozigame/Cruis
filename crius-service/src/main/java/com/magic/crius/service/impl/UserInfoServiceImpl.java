package com.magic.crius.service.impl;

import com.magic.api.commons.model.Page;
import com.magic.crius.po.UserInfo;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.storage.db.UserInfoDbService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean updateLevel(Long userId, Long level) {
        return userInfoDbService.updateLevel(userId, level);
    }

    @Override
    public int getSummaryUserNum(UserInfo userInfo) {
        return userInfoDbService.getSummaryNum(userInfo);
    }
    
    public int batchInsert(List<UserInfo> list){
    	return userInfoDbService.batchInsert(list);
    }
    
    public int updateByPrimaryKey(UserInfo userInfo){
    	return userInfoDbService.updateByPrimaryKey(userInfo);
    }

    @Override
    public int getSummaryProxyNum(UserInfo userInfo) {
        return userInfoDbService.getProxyNum(userInfo);
    }
    
    /**
     * 根据userId查用户
     * @param userIdList
     * @param proxyIdList
     * @return
     */
    public List<UserInfo> findUserInfoList(List<Long> userIdList,
    		List<Long> proxyIdList){
    	return userInfoDbService.findUserInfoList(userIdList, proxyIdList);
    }

    @Override
    public List<UserInfo> findUserLevel(UserInfo userInfo, Page page) {
        return userInfoDbService.findUserLevel(userInfo, page);
    }
}
