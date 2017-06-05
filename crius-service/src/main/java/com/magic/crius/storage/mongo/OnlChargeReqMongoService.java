package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OnlChargeReq;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:15
 */
public interface OnlChargeReqMongoService {

    /**
     * 新增公司入款
     * @param onlChargeReq
     * @return
     */
    boolean save(OnlChargeReq onlChargeReq);

    /**
     * 保存失败的数据
     * @param onlChargeReq
     * @return
     */
    boolean saveFailedData(OnlChargeReq onlChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OnlChargeReq getByReqId(Long id);
}
