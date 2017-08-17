package com.magic.crius.service.impl;

import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.service.UserPreferentialDetailService;
import com.magic.crius.storage.db.UserPreferentialDetailDbService;
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
public class UserPreferentialDetailServiceImpl implements UserPreferentialDetailService {

    @Resource
    private UserPreferentialDetailDbService userPreferentialDetailDbService;


    @Override
    public boolean insert(UserPreferentialDetail detail) {
        return userPreferentialDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<UserPreferentialDetail> details) {
        return userPreferentialDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(UserPreferentialDetail detail) {
        return userPreferentialDetailDbService.updateDetail(detail);
    }

    @Override
    public List<UserPreferentialDetail> findByUserIds(Collection<Long> userIds, Integer pdate) {
        return userPreferentialDetailDbService.findByUserIds(userIds, pdate);
    }

    @Override
    public UserPreferentialDetail getByBillId(Long billId) {
        return userPreferentialDetailDbService.getByBillId(billId);
    }

    @Override
    public int repairDetail(UserPreferentialDetail detail) {
        return userPreferentialDetailDbService.repairDetail(detail);
    }
}
