package com.magic.crius.dao.db;

import com.magic.crius.po.UserFlowMoneyDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 会员入款明细
 */
@Component
public interface UserFlowMoneyDetailMapper {


    /**
     * @param detail
     * @return
     */
    int insert(UserFlowMoneyDetail detail);


    /**
     * @param details
     * @return
     */
    int batchInsert(List<UserFlowMoneyDetail> details);

}