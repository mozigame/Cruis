package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyFlowDetail;

import java.util.Collection;
import java.util.List;

public interface OwnerCompanyFlowDetailService {
    /**
     * 添加
     * @param detail
     * @return
     */
    boolean insert(OwnerCompanyFlowDetail detail);

    /**
     * 批量添加
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyFlowDetail> details);


    /**
     * 修改
     * @param detail
     * @return
     */
    boolean updateDetail(OwnerCompanyFlowDetail detail);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}