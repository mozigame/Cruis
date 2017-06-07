package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:54
 */
public interface OwnerCompanyAccountSummmaryDbService {

    /**
     * 添加
     * @param summmary
     * @return
     */
    boolean insert(OwnerCompanyAccountSummmary summmary);

    /**
     * 添加
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyAccountSummmary> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerCompanyAccountSummmary summmary);

    /**
     * 查询多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}
