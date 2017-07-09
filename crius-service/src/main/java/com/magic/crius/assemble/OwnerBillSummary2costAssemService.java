package com.magic.crius.assemble;

import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.service.OwnerBillSummary2costService;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.OwnerBillReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:25
 * 业主成本部分退佣汇总
 */
@Service
public class OwnerBillSummary2costAssemService {

    @Resource
    private OwnerBillSummary2costService ownerBillSummary2costService;

    public boolean save(OwnerBillSummary2cost cost) {
        return ownerBillSummary2costService.save(cost);
    }

    public boolean batchInsert(List<OwnerBillSummary2cost> costs) {
        return ownerBillSummary2costService.batchInsert(costs);
    }


}
