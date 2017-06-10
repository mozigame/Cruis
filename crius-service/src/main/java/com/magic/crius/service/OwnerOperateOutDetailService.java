package com.magic.crius.service;

import com.magic.crius.po.OwnerOperateOutDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 14:59
 * 人工出款详情
 */
public interface OwnerOperateOutDetailService {


    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerOperateOutDetail summmary);

    /**
     * 批量添加
     *
     * @param details
     * @return
     */
    boolean batchInsert(Collection<OwnerOperateOutDetail> details);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateDetail(OwnerOperateOutDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerOperateOutDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
