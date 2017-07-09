package com.magic.crius.assemble;

import com.magic.crius.po.GameInfo;
import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.service.OwnerBillSummary2gameService;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.OwnerHallBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:30
 */
@Service
public class OwnerBillSummary2gameAssemService {


    @Resource
    private OwnerBillSummary2gameService ownerBillSummary2gameService;

    public boolean save(OwnerBillSummary2game summary2game) {
        return ownerBillSummary2gameService.save(summary2game);
    }

    public boolean batchInsert(List<OwnerBillSummary2game> list) {
        return ownerBillSummary2gameService.batchInsert(list);
    }

}

