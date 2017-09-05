package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserPreferentialDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 会员优惠汇总
 */
@Component
public interface UserPreferentialDetailMapper {


    /**
     * @param detail
     * @return
     */
    int insert(UserPreferentialDetail detail);

    /**
     * 修改
     * @param detail
     * @return
     */
    int updateDetail(UserPreferentialDetail detail);

    /**
     * 批量添加
     * @param details
     * @return
     */
    int batchInsert(@Param("list") Collection<UserPreferentialDetail> details);

    /**
     * @return
     */
    List<UserPreferentialDetail> findByUserIds(@Param("list") Collection<Long> userIds, @Param("pdate") Integer pdate);

    int repairDetail(UserPreferentialDetail detail);

    /**
     * 修复接口用
     * @param detail
     * @return
     */
    List<UserPreferentialDetail> selectByDetail(UserPreferentialDetail detail);

    /**
     * 修复billId和reqId
     * @param id
     * @param billId
     * @param reqId

     * @return
     */
    int repairBillIdById(@Param("id")long id, @Param("billId")long billId, @Param("reqId")long reqId);

    UserPreferentialDetail getByBillId(Long billId);
}