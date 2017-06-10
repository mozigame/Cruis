package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyAccountDetail;

import java.util.Collection;
import java.util.List;

public interface OwnerCompanyAccountSummmaryService {

    /**
     * 添加
     * @param Summmary
     * @return
     */
    boolean insert(OwnerCompanyAccountDetail Summmary);

    /**
     * 批量添加
     * @param Summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyAccountDetail> Summmaries);


    /**
     * 修改
     * @param Summmary
     * @return
     */
    boolean updateSummary(OwnerCompanyAccountDetail Summmary);

    /**
     * 查询多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}