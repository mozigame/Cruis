package com.magic.crius.storage.mongo;

import com.magic.crius.vo.JpReq;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:39
 * 彩金
 */
public interface JpReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(JpReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(JpReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    JpReq getByReqId(Long id);
}
