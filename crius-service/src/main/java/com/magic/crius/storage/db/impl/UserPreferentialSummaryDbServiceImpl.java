package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserPreferentialSummaryMapper;
import com.magic.crius.po.UserPreferentialSummary;
import com.magic.crius.storage.db.UserPreferentialSummaryDbService;
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
public class UserPreferentialSummaryDbServiceImpl implements UserPreferentialSummaryDbService {

    @Resource
    private UserPreferentialSummaryMapper userPreferentialSummaryMapper;

    @Override
    public boolean insert(UserPreferentialSummary summmary) {
        return userPreferentialSummaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<UserPreferentialSummary> summmaries) {
        return userPreferentialSummaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(UserPreferentialSummary summmary) {
        return userPreferentialSummaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<UserPreferentialSummary> findByOwnerIds(Collection<Long> userIds, Integer pdate) {
        return userPreferentialSummaryMapper.findByUserIds(userIds, pdate);
    }
}
