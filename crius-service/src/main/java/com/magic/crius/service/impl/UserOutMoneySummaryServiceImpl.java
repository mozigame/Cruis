package com.magic.crius.service.impl;

import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.UserOutMoneySummaryService;
import com.magic.crius.storage.db.UserOutMoneySummaryDbService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:27
 */
@Service
public class UserOutMoneySummaryServiceImpl implements UserOutMoneySummaryService {

    @Resource
    private UserOutMoneySummaryDbService userOutMoneySummaryDbService;


    @Override
    public boolean insert(UserOutMoneySummary summmary) {
        return userOutMoneySummaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<UserOutMoneySummary> summmaries) {
        return userOutMoneySummaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(UserOutMoneySummary summmary) {
        return userOutMoneySummaryDbService.updateSummary(summmary);
    }

    @Override
    public List<UserOutMoneySummary> findByOwnerIds(Collection<Long> userIds, Integer pdate) {
        return userOutMoneySummaryDbService.findByOwnerIds(userIds, pdate);
    }
}
