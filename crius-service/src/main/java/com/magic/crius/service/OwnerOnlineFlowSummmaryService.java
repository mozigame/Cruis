package com.magic.crius.service;

import com.magic.crius.po.OwnerOnlineFlowSummmary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:16
 */
public interface OwnerOnlineFlowSummmaryService {

    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerOnlineFlowSummmary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerOnlineFlowSummmary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerOnlineFlowSummmary summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerOnlineFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
