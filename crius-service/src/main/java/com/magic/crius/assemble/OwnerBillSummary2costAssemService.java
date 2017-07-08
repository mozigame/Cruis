package com.magic.crius.assemble;

import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.service.OwnerBillSummary2costService;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:25
 * 业主成本部分退佣汇总
 */
public class OwnerBillSummary2costAssemService {

    @Resource
    private OwnerBillSummary2costService ownerBillSummary2costService;

    public boolean save(OwnerBillSummary2cost cost) {
        return ownerBillSummary2costService.save(cost);
    }
}
