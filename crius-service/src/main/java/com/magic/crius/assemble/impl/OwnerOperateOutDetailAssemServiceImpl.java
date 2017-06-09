package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.service.OwnerOperateOutDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:02
 */
@Service
public class OwnerOperateOutDetailAssemServiceImpl implements OwnerOperateOutDetailAssemService {

    @Resource
    private OwnerOperateOutDetailService ownerOperateOutDetailService;

    @Override
    public void batchSave(List<OwnerOperateOutDetail> ownerOperateOutDetails) {
        //todo 错误处理
        if (ownerOperateOutDetails.size() > 0) {
            boolean saveResult = ownerOperateOutDetailService.batchInsert(ownerOperateOutDetails);
        }

    }
}
