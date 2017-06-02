package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OperateChargeReq;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:46
 */
public interface OperateChargeMongoService {

    /**
     * 新增人工充值
     * @param operateChargeReq
     * @return
     */
    boolean save(OperateChargeReq operateChargeReq);

    /**
     * 保存失败的数据
     * @param operateChargeReq
     * @return
     */
    boolean saveFailedData(OperateChargeReq operateChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OperateChargeReq getByReqId(Long id);
}
