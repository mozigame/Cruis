package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyAccountSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyAccountDetail;
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
    public void batchSave(List<OwnerCompanyAccountDetail> ownerCompanyAccountSummmaries) {

        //todo 错误处理
        if (ownerCompanyAccountSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyAccountSummmaryService.batchInsert(ownerCompanyAccountSummmaries);
        }

    }
}
