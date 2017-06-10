package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOnlineFlowSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OwnerOnlineFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 17:18
 */
@Service
public class OwnerOnlineFlowSummmaryAssemServiceImpl implements OwnerOnlineFlowSummmaryAssemService {

    @Resource
    private OwnerOnlineFlowSummmaryService ownerOnlineFlowSummmaryService;

    @Override
    public void batchSave(List<OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaries) {

        //todo 错误处理
        if (ownerOnlineFlowSummmaries.size() > 0) {
            boolean saveResult = ownerOnlineFlowSummmaryService.batchInsert(ownerOnlineFlowSummmaries);
        }
    }
}
