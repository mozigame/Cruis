package com.magic.crius.storage.mongo;

import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:59
 * 会员mongo信息
 */
public interface MemberConditionVoMongoService {

    /**
     * 修改入款信息
     * @param vo
     * @return
     */
    boolean updateRecharge(MemberConditionVo vo);

    /**
     * 修改出款信息
     * @param vo
     * @return
     */
    boolean updateWithdraw(MemberConditionVo vo);

    /**
     * 修改会员层级
     * @param userLevelReq
     * @return
     */
    boolean updateLevel(UserLevelReq userLevelReq);

}
