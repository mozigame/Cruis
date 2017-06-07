package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerAwardDetailAssemService;
import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.service.OwnerAwardDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:35
 */
@Service
public class OwnerAwardDetailAssemServiceImpl implements OwnerAwardDetailAssemService {

    @Resource
    private OwnerAwardDetailService ownerAwardDetailService;

    @Override
    public boolean batchSave(List<OwnerAwardDetail> details) {
        return ownerAwardDetailService.batchInsert(details);
    }
}
