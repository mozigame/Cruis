package com.magic.crius.storage.mongo;

import com.magic.crius.vo.PreCmpChargeReq;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
public interface PreCmpChargeMongoService {

    /**
     * 新增公司入款
     * @param preCmpChargeReq
     * @return
     */
    boolean save(PreCmpChargeReq preCmpChargeReq);

    /**
     * 保存失败的数据
     * @param preCmpChargeReq
     * @return
     */
    boolean saveFailedData(PreCmpChargeReq preCmpChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    PreCmpChargeReq getByReqId(Long id);
}
