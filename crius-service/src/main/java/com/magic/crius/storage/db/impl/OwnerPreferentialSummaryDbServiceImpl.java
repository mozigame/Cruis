package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerPreferentialSummaryMapper;
import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.storage.db.OwnerPreferentialSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:32
 */
@Service
public class OwnerPreferentialSummaryDbServiceImpl implements OwnerPreferentialSummaryDbService {

    @Resource
    private OwnerPreferentialSummaryMapper ownerPreferentialSummaryMapper;

    @Override
    public boolean save(OwnerPreferentialSummary record) {
        return ownerPreferentialSummaryMapper.insert(record) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Integer preferentialType, Integer pdate) {
        return ownerPreferentialSummaryMapper.checkExist(ownerId, preferentialType, pdate) > 0;
    }

    @Override
    public boolean updateSummary(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryMapper.updateSummary(summmary) > 0;
    }
}
