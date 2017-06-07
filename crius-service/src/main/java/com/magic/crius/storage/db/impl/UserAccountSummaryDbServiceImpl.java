package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserAccountSummaryMapper;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.storage.db.UserAccountSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:19
 */
@Service
public class UserAccountSummaryDbServiceImpl implements UserAccountSummaryDbService {

    @Resource
    private UserAccountSummaryMapper userAccountSummaryMapper;

    @Override
    public boolean insert(UserAccountSummary summmary) {
        return userAccountSummaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<UserAccountSummary> summmaries) {
        return userAccountSummaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(UserAccountSummary summmary) {
        return userAccountSummaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<UserAccountSummary> findByOwnerIds(Collection<Long> userIds, Integer pdate) {
        return userAccountSummaryMapper.findByUserIds(userIds, pdate);
    }
}
