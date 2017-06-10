package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserOutMoneyDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 会员出款明细
 */
@Component
public interface UserOutMoneyDetailMapper {

    int insert(UserOutMoneyDetail record);

    int batchInsert(List<UserOutMoneyDetail> userOutMoneyDetails);

}