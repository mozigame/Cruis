package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerInfo;
import com.magic.crius.service.OwnerInfoService;
import com.magic.crius.storage.db.OwnerInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:56
 */
@Service
public class OwnerInfoServiceImpl implements OwnerInfoService {

    @Resource
    private OwnerInfoDbService ownerInfoDbService;

    @Override
    public boolean insert(OwnerInfo record) {
        return ownerInfoDbService.insert(record);
    }

    @Override
    public boolean batchInsert(List<OwnerInfo> infos) {
        return ownerInfoDbService.batchInsert(infos);
    }

    @Override
    public OwnerInfo get(Long ownerId) {
        return ownerInfoDbService.get(ownerId);
    }
}
