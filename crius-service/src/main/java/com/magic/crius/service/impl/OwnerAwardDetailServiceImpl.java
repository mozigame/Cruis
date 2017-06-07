package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.service.OwnerAwardDetailService;
import com.magic.crius.storage.db.OwnerAwardDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:33
 * 打赏明细
 */
@Service
public class OwnerAwardDetailServiceImpl implements OwnerAwardDetailService {

    @Resource
    private OwnerAwardDetailDbService ownerAwardDetailDbService;

    @Override
    public boolean insert(OwnerAwardDetail record) {
        return ownerAwardDetailDbService.insert(record);
    }

    @Override
    public boolean batchInsert(List<OwnerAwardDetail> details) {
        return ownerAwardDetailDbService.batchInsert(details);
    }
}
