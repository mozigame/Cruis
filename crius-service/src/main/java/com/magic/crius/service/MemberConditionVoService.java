package com.magic.crius.service;

import com.magic.user.vo.MemberConditionVo;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:26
 */
public interface MemberConditionVoService {

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
}
