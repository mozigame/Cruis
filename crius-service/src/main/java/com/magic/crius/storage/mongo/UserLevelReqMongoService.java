package com.magic.crius.storage.mongo;

import com.magic.crius.vo.UserLevelReq;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:08
 */
public interface UserLevelReqMongoService {
    /**
     * 修改会员的层级
     * @param userLevelReq
     * @return
     */
    boolean updateLevel(UserLevelReq userLevelReq);
}
