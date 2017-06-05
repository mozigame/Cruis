package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserOutMoneySummaryMapper;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.storage.db.UserOutMoneySummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public boolean save(UserOutMoneySummary record) {
        return userOutMoneySummaryMapper.insert(record) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Long userId, Integer pdate) {
        return userOutMoneySummaryMapper.checkExist(ownerId, userId, pdate) > 0;
    }

    @Override
    public boolean updateSummary(UserOutMoneySummary summmary) {
        return userOutMoneySummaryMapper.updateSummary(summmary) > 0;
    }
}
