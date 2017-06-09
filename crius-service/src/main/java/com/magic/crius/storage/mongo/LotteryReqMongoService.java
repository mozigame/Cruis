package com.magic.crius.storage.mongo;

import com.magic.crius.vo.LotteryReq;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:29
 */
public interface LotteryReqMongoService {

    /**
     * @param
     * @return
     */
    boolean save(LotteryReq req);

    /**
     * 保存失败的数据
     * @param
     * @return
     */
    boolean saveFailedData(LotteryReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LotteryReq getByReqId(Long id);
}
