package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerBillSummary2costMapper;
import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.storage.db.OwnerBillSummary2costDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:21
 */
@Service
public class OwnerBillSummary2costDbServiceImpl implements OwnerBillSummary2costDbService {


    @Resource
    private OwnerBillSummary2costMapper ownerBillSummary2costMapper;


    @Override
    public boolean save(OwnerBillSummary2cost cost) {
        return ownerBillSummary2costMapper.insert(cost) > 0;
    }

    @Override
    public boolean batchInsert(List<OwnerBillSummary2cost> costs) {
        return ownerBillSummary2costMapper.batchInsert(costs) > 0;
    }
}
