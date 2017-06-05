package com.magic.crius.service.impl;

import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.UserOutMoneySummaryService;
import com.magic.crius.storage.db.UserOutMoneySummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public boolean save(UserOutMoneySummary summmary) {
        return userOutMoneySummaryDbService.save(summmary);
    }

    @Override
    public boolean updateSummary(UserOutMoneySummary summmary) {
        return userOutMoneySummaryDbService.updateSummary(summmary);
    }

    @Override
    public boolean checkExist(Long ownerId, Long userId, Integer pdate) {
        return userOutMoneySummaryDbService.checkExist(ownerId, userId, pdate);
    }
}
