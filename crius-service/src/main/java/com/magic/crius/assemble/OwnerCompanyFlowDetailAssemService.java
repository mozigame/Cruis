package com.magic.crius.assemble;

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
public class OwnerCompanyFlowDetailAssemService  {

    @Resource
    private OwnerCompanyFlowDetailService ownerCompanyFlowDetailService;

    public void batchSave(List<OwnerCompanyFlowDetail> ownerCompanyFlowSummmaries) {

        //todo 错误处理
        if (ownerCompanyFlowSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyFlowDetailService.batchInsert(ownerCompanyFlowSummmaries);
        }

    }
}