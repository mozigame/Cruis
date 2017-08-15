package com.magic.crius.storage.db.impl;

import com.magic.api.commons.model.Page;
import com.magic.crius.dao.crius.db.UserInfoMapper;
import com.magic.crius.po.UserInfo;
import com.magic.crius.storage.db.UserInfoDbService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 11:47
 */
@Service
public class UserInfoDbServiceImpl implements UserInfoDbService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean save(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo) > 0;
    }

    @Override
    public UserInfo get(Long userId) {
        return userInfoMapper.get(userId);
    }

    @Override
    public boolean updateLevel(Long userId, Long level) {
        return userInfoMapper.updateLevel(userId, level) > 0;
    }
    
    public int getSummaryNum(UserInfo record){
    	 return userInfoMapper.getSummaryNum(record);
    }

	@Override
	public int getProxyNum(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfoMapper.getProxyNum(record);
	}
	
	public int batchInsert(List<UserInfo> list){
		return userInfoMapper.batchInsert(list);
	}
	
	public int updateByPrimaryKey(UserInfo userInfo){
		return userInfoMapper.updateByPrimaryKey(userInfo);
	}
	
	public List<UserInfo> findUserInfoList(List<Long> userIdList,
    		List<Long> proxyIdList){
		return userInfoMapper.findUserInfoList(userIdList, proxyIdList);
	}

    @Override
    public List<UserInfo> findUserLevel(UserInfo userInfo, Page page) {
        Integer offset = (page.getPageNo() - 1) * page.getPageSize();
        return userInfoMapper.findUserLevel(userInfo, offset, page.getPageSize());
    }
}
