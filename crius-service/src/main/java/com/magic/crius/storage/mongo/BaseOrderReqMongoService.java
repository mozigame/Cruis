package com.magic.crius.storage.mongo;

import com.magic.crius.vo.BaseOrderReq;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:10
 * 视讯注单
 */
public interface BaseOrderReqMongoService {


    /**
     * @param
     * @return
     */
    boolean save(BaseOrderReq req);

    /**
     * 保存失败的数据
     * @param
     * @return
     */
    boolean saveFailedData(BaseOrderReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    BaseOrderReq getByReqId(Long id);
}
