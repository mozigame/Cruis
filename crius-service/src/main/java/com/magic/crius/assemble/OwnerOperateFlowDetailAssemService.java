package com.magic.crius.assemble;

import com.magic.crius.po.OwnerOperateFlowDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:19
 * 人工入款汇总
 */
public interface OwnerOperateFlowDetailAssemService {

    /**
     * 批量添加人工入款汇总
     * @param ownerOnlineFlowSummmaries
     */
    void batchSave(List<OwnerOperateFlowDetail> ownerOnlineFlowSummmaries);
}
