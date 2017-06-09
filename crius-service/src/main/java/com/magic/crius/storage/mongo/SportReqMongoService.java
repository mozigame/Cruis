package com.magic.crius.storage.mongo;

import com.magic.crius.vo.LotteryReq;
import com.magic.crius.vo.SportReq;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:05
 * 体育注单
 */
public interface SportReqMongoService {

    /**
     * @param
     * @return
     */
    boolean save(SportReq req);

    /**
     * 保存失败的数据
     * @param
     * @return
     */
    boolean saveFailedData(SportReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    SportReq getByReqId(Long id);
}
