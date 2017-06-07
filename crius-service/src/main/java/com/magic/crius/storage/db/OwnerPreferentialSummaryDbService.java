package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerPreferentialSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:31
 * 优惠汇总
 */
public interface OwnerPreferentialSummaryDbService {


    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerPreferentialSummary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerPreferentialSummary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerPreferentialSummary summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerPreferentialSummary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
