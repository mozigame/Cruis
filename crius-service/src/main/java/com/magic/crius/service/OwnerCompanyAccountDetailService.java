package com.magic.crius.service;

import com.magic.crius.po.OwnerCompanyAccountDetail;

import java.util.Collection;
import java.util.List;

public interface OwnerCompanyAccountDetailService {

    /**
     * 添加
     * @param detail
     * @return
     */
    boolean insert(OwnerCompanyAccountDetail detail);

    /**
     * 批量添加
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyAccountDetail> details);


    /**
     * 修改
     * @param detail
     * @return
     */
    boolean updateDetail(OwnerCompanyAccountDetail detail);

    /**
     * 查询多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}