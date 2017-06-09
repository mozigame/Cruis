package com.magic.crius.service;

import com.magic.crius.po.OwnerReforwardMoneyToGame;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 19:28
 */
public interface OwnerReforwardMoneyToGameService {


    /**
     * 添加
     *
     * @param summmary
     * @return
     */
    boolean insert(OwnerReforwardMoneyToGame summmary);

    /**
     * 批量添加
     *
     * @param summmaries
     * @return
     */
    boolean batchInsert(Collection<OwnerReforwardMoneyToGame> summmaries);


    /**
     * 修改
     *
     * @param summmary
     * @return
     */
    boolean updateSummary(OwnerReforwardMoneyToGame summmary);

    /**
     * 查询当天内多个业主下的数据
     *
     * @return
     */
    List<OwnerReforwardMoneyToGame> findByOwnerIds(Collection<Long> ownerIds, Integer pdate);
}
