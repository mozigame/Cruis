package com.magic.crius.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.po.RiskEventRecord;
import com.magic.crius.service.RiskEventRecordService;
import com.magic.crius.storage.db.RiskEventRecordDbService;

/**
 * User: justin
 * Date: 2017/07/18
 */
@Service
public class RiskEventRecordServiceImpl implements RiskEventRecordService {

    @Resource
    private RiskEventRecordDbService riskEventRecordDbService;

    @Override
    public boolean insert(RiskEventRecord record) {
        return riskEventRecordDbService.insert(record);
    }
}
