package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOnlineFlowDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:59
 */
public interface OwnerOnlineFlowDetailDbService {

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
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerOnlineFlowDetail> summmaries);


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
