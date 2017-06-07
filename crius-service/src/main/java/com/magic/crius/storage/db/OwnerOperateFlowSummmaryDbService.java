package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:08
 */
public interface OwnerOperateFlowSummmaryDbService {

    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerOperateFlowSummmary summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerOperateFlowSummmary> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerOperateFlowSummmary summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerOperateFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
