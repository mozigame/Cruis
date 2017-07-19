package com.magic.crius.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.po.RiskEventDetail;
import com.magic.crius.service.RiskEventDetailService;
import com.magic.crius.storage.db.RiskEventDetailDbService;

/**
 * User: justin
 * Date: 2017/07/18
 */
@Service
public class RiskEventDetailServiceImpl implements RiskEventDetailService {

    @Resource
    private RiskEventDetailDbService riskEventDetailDbService;

    @Override
    public boolean insert(RiskEventDetail record) {
        return riskEventDetailDbService.insert(record);
    }

    @Override
    public boolean batchInsert(List<RiskEventDetail> infos) {
        return riskEventDetailDbService.batchInsert(infos);
    }
}
