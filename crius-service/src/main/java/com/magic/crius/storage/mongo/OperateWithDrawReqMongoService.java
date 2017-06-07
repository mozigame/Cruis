package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OperateWithDrawReq;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:38
 * 人工提现
 */
public interface OperateWithDrawReqMongoService {
    /**
     * @param req
     * @return
     */
    boolean save(OperateWithDrawReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(OperateWithDrawReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OperateWithDrawReq getByReqId(Long id);
}
