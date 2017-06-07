package com.magic.crius.storage.mongo;

import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DiscountReq;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:39
 * 返水
 */
public interface CashbackReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(CashbackReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(CashbackReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    CashbackReq getByReqId(Long id);
}
