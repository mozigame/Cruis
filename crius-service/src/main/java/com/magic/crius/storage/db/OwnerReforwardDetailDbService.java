package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerReforwardDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:20
 * 反水详情
 */
public interface OwnerReforwardDetailDbService {


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
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerReforwardDetail> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerReforwardDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerReforwardDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
