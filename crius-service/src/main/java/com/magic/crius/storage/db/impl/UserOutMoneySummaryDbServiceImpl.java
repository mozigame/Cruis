package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.UserOutMoneySummaryMapper;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.storage.db.UserOutMoneySummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:24
 */
@Service
public class UserOutMoneySummaryDbServiceImpl implements UserOutMoneySummaryDbService {

    @Resource
    private UserOutMoneySummaryMapper userOutMoneySummaryMapper;


    @Override
    public boolean insert(UserOutMoneySummary summmary) {
        return userOutMoneySummaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<UserOutMoneySummary> summmaries) {
        return userOutMoneySummaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(UserOutMoneySummary summmary) {
        return userOutMoneySummaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<UserOutMoneySummary> findByUserIds(Collection<Long> userIds, Integer pdate) {
        return userOutMoneySummaryMapper.findByUserIds(userIds, pdate);
    }
}
