package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.service.OwnerBillSummary2gameService;
import com.magic.crius.storage.db.OwnerBillSummary2gameDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:29
 */
@Service
public class OwnerBillSummary2gameServiceImpl implements OwnerBillSummary2gameService {

    @Resource
    private OwnerBillSummary2gameDbService ownerBillSummary2gameDbService;

    @Override
    public boolean save(OwnerBillSummary2game summary2game) {
        return ownerBillSummary2gameDbService.save(summary2game);
    }
}
