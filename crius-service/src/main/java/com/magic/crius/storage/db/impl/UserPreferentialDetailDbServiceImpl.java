package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.UserPreferentialDetailMapper;
import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.storage.db.UserPreferentialDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:24
 */
@Service
public class UserPreferentialDetailDbServiceImpl implements UserPreferentialDetailDbService {

    @Resource
    private UserPreferentialDetailMapper userPreferentialDetailMapper;

    @Override
    public boolean insert(UserPreferentialDetail summmary) {
        return userPreferentialDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<UserPreferentialDetail> summmaries) {
        return userPreferentialDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateDetail(UserPreferentialDetail summmary) {
        return userPreferentialDetailMapper.updateDetail(summmary) > 0;
    }

    @Override
    public List<UserPreferentialDetail> findByUserIds(Collection<Long> userIds, Integer pdate) {
        return userPreferentialDetailMapper.findByUserIds(userIds, pdate);
    }
}
