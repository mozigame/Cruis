package com.magic.crius.assemble;

import com.magic.crius.po.OwnerReforwardDetail;

import java.util.Collection;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:27
 * 返水详情
 */
public interface OwnerReforwardDetailAssemService {

    /**
     * 批量添加返水详情
     * @param userOutMoneySummaries
     */
    boolean batchSave(Collection<OwnerReforwardDetail> userOutMoneySummaries);

}
