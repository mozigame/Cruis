package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerCompanyAccountDetail;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:54
 */
public interface OwnerCompanyAccountDetailDbService {

    /**
     * 添加
     * @param summmary
     * @return
     */
    boolean insert(OwnerCompanyAccountDetail summmary);

    /**
     * 添加
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerCompanyAccountDetail> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateDetail(OwnerCompanyAccountDetail summmary);

    /**
     * 查询多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);

}
