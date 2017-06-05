package com.magic.crius.assemble;

import com.magic.crius.po.OwnerOperateFlowSummmary;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:19
 * 人工入款汇总
 */
public interface OwnerOperateFlowSummmaryAssemService {

    /**
     * 批量添加人工入款汇总
     * @param ownerOnlineFlowSummmaries
     */
    void batchSave(Collection<OwnerOperateFlowSummmary> ownerOnlineFlowSummmaries);
}
