package com.magic.crius.assemble;

import com.magic.crius.po.OwnerOnlineFlowDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 17:17
 * 线上入款汇总
 */
public interface OwnerOnlineFlowDetailAssemService {

    /**
     * 批量添加线上入款汇总
     * @param ownerOnlineFlowSummmaries
     */
    void batchSave(List<OwnerOnlineFlowDetail> ownerOnlineFlowSummmaries);
}
