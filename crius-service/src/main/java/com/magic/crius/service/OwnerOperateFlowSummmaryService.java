package com.magic.crius.service;

import com.magic.crius.po.OwnerOperateFlowDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:11
 * 人工入款汇总
 */
public interface OwnerOperateFlowSummmaryService {
    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerOperateFlowDetail summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerOperateFlowDetail> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerOperateFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerOperateFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
