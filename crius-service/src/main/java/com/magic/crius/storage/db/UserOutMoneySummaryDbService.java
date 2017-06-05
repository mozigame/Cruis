package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.po.UserOutMoneySummary;
import org.apache.ibatis.annotations.Param;

public interface UserOutMoneySummaryDbService {
    /**
     * 添加会员出款汇总
     * @param record
     * @return
     */
    boolean save(UserOutMoneySummary record);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param userId
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Long userId, Integer pdate);

    /**
     * 修改
     * @param summmary
     * @return
     */
    boolean updateSummary(UserOutMoneySummary summmary);
}