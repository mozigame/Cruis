package com.magic.crius.service;

import com.magic.crius.po.OwnerOnlineFlowDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:16
 */
public interface OwnerOnlineFlowDetailService {

    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerOnlineFlowDetail summmary);

    /**
     * 批量添加
     *
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerOnlineFlowDetail> details);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateDetail(OwnerOnlineFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerOnlineFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
