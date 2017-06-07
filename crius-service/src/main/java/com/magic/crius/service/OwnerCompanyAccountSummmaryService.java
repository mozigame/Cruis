package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyAccountSummmary;

import java.util.Collection;
import java.util.List;

public interface OwnerCompanyAccountSummmaryService {

    /**
     * 添加
     * @param Summmary
     * @return
     */
    boolean insert(OwnerCompanyAccountSummmary Summmary);

    /**
     * 批量添加
     * @param Summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyAccountSummmary> Summmaries);


    /**
     * 修改
     * @param Summmary
     * @return
     */
    boolean updateSummary(OwnerCompanyAccountSummmary Summmary);

    /**
     * 查询多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}