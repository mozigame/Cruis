package com.magic.crius.storage.db.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.dao.crius.db.RiskEventRecordMapper;
import com.magic.crius.po.RiskEventRecord;
import com.magic.crius.storage.db.RiskEventRecordDbService;

/**
 * User: justin
 * Date: 2017/07/18
 */
@Service
public class RiskEventRecordDbServiceImpl implements RiskEventRecordDbService {

    @Resource
    private RiskEventRecordMapper riskEventRecordMapper;

    @Override
    public boolean insert(RiskEventRecord record) {
        return riskEventRecordMapper.insert(record) > 0;
    }
}
