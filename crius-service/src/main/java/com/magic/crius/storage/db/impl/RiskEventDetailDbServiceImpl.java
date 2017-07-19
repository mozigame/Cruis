package com.magic.crius.storage.db.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.dao.crius.db.RiskEventDetailMapper;
import com.magic.crius.po.RiskEventDetail;
import com.magic.crius.storage.db.RiskEventDetailDbService;

/**
 * User: justin
 * Date: 2017/07/18
 */
@Service
public class RiskEventDetailDbServiceImpl implements RiskEventDetailDbService {

    @Resource
    private RiskEventDetailMapper riskEventDetailMapper;

    @Override
    public boolean insert(RiskEventDetail record) {
        return riskEventDetailMapper.insert(record) > 0;
    }

    @Override
    public boolean batchInsert(List<RiskEventDetail> infos) {
        return riskEventDetailMapper.batchInsert(infos) > 0;
    }
}
