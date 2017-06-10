package com.magic.crius.service;

import com.magic.crius.po.OwnerPreferentialDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 18:23
 * 优惠汇总
 */
public interface OwnerPreferentialDetailService {

    /**
     * 添加
     *
     * @param detail
     * @return
     */
    boolean insert(OwnerPreferentialDetail detail);

    /**
     * 批量添加
     *
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerPreferentialDetail> details);


    /**
     * 修改
     *
     * @param detail
     * @return
     */
    boolean updateDetail(OwnerPreferentialDetail detail);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerPreferentialDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
