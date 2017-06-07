package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyFlowSummmary;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
public interface OwnerCompanyFlowSummmaryDbService {

    /**
     * 添加
     * @param summmary
     * @return
     */
    boolean insert(OwnerCompanyFlowSummmary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyFlowSummmary> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerCompanyFlowSummmary summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
