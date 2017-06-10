package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyFlowSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司入款明细
 */
@Service("ownerCompanyFlowSummmaryAssemService")
public class OwnerCompanyFlowSummmaryAssemServiceImpl implements OwnerCompanyFlowSummmaryAssemService {

    @Resource
    private OwnerCompanyFlowSummmaryService ownerCompanyFlowSummmaryService;

    @Override
    public void batchSave(List<OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaries) {

        //todo 错误处理
        if (ownerCompanyFlowSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyFlowSummmaryService.batchInsert(ownerCompanyFlowSummmaries);
        }

    }
}