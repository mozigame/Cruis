package com.magic.crius.assemble;

import com.magic.crius.po.OwnerOperateOutDetail;

import java.util.Collection;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:01
 * 人工出款详情
 */
public interface OwnerOperateOutDetailAssemService {

    /**
     * 批量人工出款详情
     * @param ownerOperateOutDetails
     */
    void batchSave(Map<String, OwnerOperateOutDetail> ownerOperateOutDetails);
}
