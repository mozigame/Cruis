package com.magic.crius.service;

import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.OwnerPreferentialSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 18:23
 * 优惠汇总
 */
public interface OwnerPreferentialSummaryService {

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
