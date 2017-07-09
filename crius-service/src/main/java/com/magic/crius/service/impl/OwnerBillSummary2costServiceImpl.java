package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.service.OwnerBillSummary2costService;
import com.magic.crius.storage.db.OwnerBillSummary2costDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:25
 */
@Service
public class OwnerBillSummary2costServiceImpl implements OwnerBillSummary2costService {

    @Resource
    private OwnerBillSummary2costDbService ownerBillSummary2costDbService;


    @Override
    public boolean save(OwnerBillSummary2cost cost) {
        return ownerBillSummary2costDbService.save(cost);
    }

    @Override
    public boolean batchInsert(List<OwnerBillSummary2cost> costs) {
        return ownerBillSummary2costDbService.batchInsert(costs);
    }
}
