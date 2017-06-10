package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyFlowDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
public interface OwnerCompanyFlowDetailDbService {

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
    boolean updateDetail(OwnerCompanyFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
