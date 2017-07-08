package com.magic.crius.assemble;

import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.service.OwnerBillSummary2gameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

