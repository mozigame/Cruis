package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyAccountSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyAccountSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:52
 * 公司账目汇总
 */
@Service("ownerCompanyAccountSummmaryAssemService")
public class OwnerCompanyAccountSummmaryAssemServiceImpl implements OwnerCompanyAccountSummmaryAssemService {

    @Resource
    private OwnerCompanyAccountSummmaryService ownerCompanyAccountSummmaryService;

    @Override
    public void batchSave(List<OwnerCompanyAccountSummmary> ownerCompanyAccountSummmaries) {

        //todo 错误处理
        if (ownerCompanyAccountSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyAccountSummmaryService.batchInsert(ownerCompanyAccountSummmaries);
        }

    }
}
