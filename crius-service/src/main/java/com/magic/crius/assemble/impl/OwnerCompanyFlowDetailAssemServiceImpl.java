package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyFlowDetailAssemService;
import com.magic.crius.po.OwnerCompanyFlowDetail;
import com.magic.crius.service.OwnerCompanyFlowDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司入款明细
 */
@Service("ownerCompanyFlowDetailAssemService")
public class OwnerCompanyFlowDetailAssemServiceImpl implements OwnerCompanyFlowDetailAssemService {

    @Resource
    private OwnerCompanyFlowDetailService ownerCompanyFlowDetailService;

    @Override
    public void batchSave(List<OwnerCompanyFlowDetail> ownerCompanyFlowSummmaries) {

        //todo 错误处理
        if (ownerCompanyFlowSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyFlowDetailService.batchInsert(ownerCompanyFlowSummmaries);
        }

    }
}