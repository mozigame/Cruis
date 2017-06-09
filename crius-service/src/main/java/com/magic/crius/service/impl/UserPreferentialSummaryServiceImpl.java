package com.magic.crius.service.impl;

import com.magic.crius.po.UserPreferentialSummary;
import com.magic.crius.service.UserPreferentialSummaryService;
import com.magic.crius.storage.db.UserPreferentialSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:29
 */
@Service
public class UserPreferentialSummaryServiceImpl implements UserPreferentialSummaryService {

    @Resource
    private UserPreferentialSummaryDbService userPreferentialSummaryDbService;


    @Override
    public boolean insert(UserPreferentialSummary summmary) {
        return userPreferentialSummaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<UserPreferentialSummary> summmaries) {
        return userPreferentialSummaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(UserPreferentialSummary summmary) {
        return userPreferentialSummaryDbService.updateSummary(summmary);
    }

    @Override
    public List<UserPreferentialSummary> findByUserIds(Collection<Long> userIds, Integer pdate) {
        return userPreferentialSummaryDbService.findByUserIds(userIds, pdate);
    }
}
