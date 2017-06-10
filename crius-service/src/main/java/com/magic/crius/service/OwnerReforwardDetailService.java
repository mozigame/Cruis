package com.magic.crius.service;

import com.magic.crius.po.OwnerReforwardDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:24
 * 返水详情
 */
public interface OwnerReforwardDetailService {


    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerReforwardDetail summmary);

    /**
     * 批量添加
     *
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerReforwardDetail> details);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateDetail(OwnerReforwardDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerReforwardDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
