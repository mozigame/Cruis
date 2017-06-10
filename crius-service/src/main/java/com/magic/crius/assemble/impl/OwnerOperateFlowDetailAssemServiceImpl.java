package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateFlowDetailAssemService;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.service.OwnerOperateFlowDetailService;
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
public class OwnerOperateFlowDetailAssemServiceImpl implements OwnerOperateFlowDetailAssemService {

    @Resource
    private OwnerOperateFlowDetailService ownerOperateFlowDetailService;

    @Override
    public void batchSave(List<OwnerOperateFlowDetail> ownerOnlineFlowSummmaries) {

        //todo 错误处理
        if (ownerOnlineFlowSummmaries.size() > 0) {
            boolean saveResult = ownerOperateFlowDetailService.batchInsert(ownerOnlineFlowSummmaries);
        }
    }
}
