package com.magic.crius.service.impl;

import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.service.UserAccountSummaryService;
import com.magic.crius.storage.db.UserAccountSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:20
 */
@Service
public class UserAccountSummaryServiceImpl implements UserAccountSummaryService {

    @Resource
    private UserAccountSummaryDbService userAccountSummaryDbService;


    @Override
    public boolean insert(UserAccountSummary summmary) {
        return userAccountSummaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<UserAccountSummary> summmaries) {
        return userAccountSummaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(UserAccountSummary summmary) {
        return userAccountSummaryDbService.updateSummary(summmary);
    }

    @Override
    public List<UserAccountSummary> findByOwnerIds(Collection<Long> userIds, Integer pdate) {
        return userAccountSummaryDbService.findByOwnerIds(userIds, pdate);
    }
}
