package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateFlowSummmaryAssemService;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.service.OwnerOperateFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:21
 * 人工入款汇总
 */
@Service
public class OwnerOperateFlowSummmaryAssemServiceImpl implements OwnerOperateFlowSummmaryAssemService {

    @Resource
    private OwnerOperateFlowSummmaryService ownerOperateFlowSummmaryService;

    @Override
    public void batchSave(List<OwnerOperateFlowDetail> ownerOnlineFlowSummmaries) {

        //todo 错误处理
        if (ownerOnlineFlowSummmaries.size() > 0) {
            boolean saveResult = ownerOperateFlowSummmaryService.batchInsert(ownerOnlineFlowSummmaries);
        }
    }
}
