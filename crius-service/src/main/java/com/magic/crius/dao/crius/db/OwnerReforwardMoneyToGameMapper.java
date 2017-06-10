package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerReforwardMoneyToGame;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface OwnerReforwardMoneyToGameMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(OwnerReforwardMoneyToGame summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerReforwardMoneyToGame summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerReforwardMoneyToGame> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerReforwardMoneyToGame> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}