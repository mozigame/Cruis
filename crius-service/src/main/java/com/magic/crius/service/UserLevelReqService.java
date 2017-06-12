package com.magic.crius.service;

import com.magic.crius.vo.UserLevelReq;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:17
 */
public interface UserLevelReqService {

    /**
     * 修改会员的层级
     * @param userLevelReq
     * @return
     */
    boolean updateLevel(UserLevelReq userLevelReq);
}
