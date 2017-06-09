package com.magic.crius.storage.mongo;

import com.magic.crius.vo.EGameReq;
import com.magic.crius.vo.LotteryReq;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:08
 */
public interface EGameReqMongoService {

    /**
     * @param
     * @return
     */
    boolean save(EGameReq req);

    /**
     * 保存失败的数据
     * @param
     * @return
     */
    boolean saveFailedData(EGameReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    EGameReq getByReqId(Long id);
}
