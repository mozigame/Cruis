package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyFlowDetail;

import java.util.Collection;
import java.util.List;

public interface OwnerCompanyFlowSummmaryService {
    /**
     * 添加
     * @param summmary
     * @return
     */
    boolean insert(OwnerCompanyFlowDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyFlowDetail> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerCompanyFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}