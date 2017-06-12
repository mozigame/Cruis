package com.magic.crius.assemble;

import com.magic.crius.assemble.OwnerOnlineFlowDetailAssemService;
import com.magic.crius.po.OwnerOnlineFlowDetail;
import com.magic.crius.service.OwnerOnlineFlowDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 17:18
 */
@Service
public class OwnerOnlineFlowDetailAssemService {

    @Resource
    private OwnerOnlineFlowDetailService ownerOnlineFlowDetailService;

    public void batchSave(List<OwnerOnlineFlowDetail> ownerOnlineFlowSummmaries) {

        //todo 错误处理
        if (ownerOnlineFlowSummmaries.size() > 0) {
            boolean saveResult = ownerOnlineFlowDetailService.batchInsert(ownerOnlineFlowSummmaries);
        }
    }
}
