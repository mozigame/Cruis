package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerPreferentialDetailAssemService;
import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.service.OwnerPreferentialDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:36
 */
@Service
public class OwnerPreferentialDetailAssemServiceImpl implements OwnerPreferentialDetailAssemService {

    @Resource
    private OwnerPreferentialDetailService ownerPreferentialDetailService;

    @Override
    public void batchSave(List<OwnerPreferentialDetail> ownerPreferentialSummaries) {

        //todo 错误处理
        if (ownerPreferentialSummaries.size() > 0) {
            boolean saveResult = ownerPreferentialDetailService.batchInsert(ownerPreferentialSummaries);
        }

    }
}
