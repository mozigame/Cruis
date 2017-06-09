package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.service.OwnerReforwardDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:27
 */
@Service
public class OwnerReforwardDetailAssemServiceImpl implements OwnerReforwardDetailAssemService {

    @Resource
    private OwnerReforwardDetailService ownerReforwardDetailService;

    @Override
    public boolean batchSave(Collection<OwnerReforwardDetail> reforwardDetails) {
        return ownerReforwardDetailService.batchInsert(reforwardDetails);
    }
}
