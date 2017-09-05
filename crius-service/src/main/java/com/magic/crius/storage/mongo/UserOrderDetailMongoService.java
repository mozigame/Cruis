package com.magic.crius.storage.mongo;

import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.vo.PreWithdrawReq;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 17:26
 * 会员的彩票、体育已派彩的注单
 */
public interface UserOrderDetailMongoService {

    /**
     * 新增会员更改派彩的失败的注单
     * @param detail
     * @return
     */
    boolean saveUpdateFailed(UserOrderDetail detail);

}
