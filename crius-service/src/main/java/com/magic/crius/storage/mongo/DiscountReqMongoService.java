package com.magic.crius.storage.mongo;

import com.magic.crius.vo.DiscountReq;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:23
 */
public interface DiscountReqMongoService {

    /**
     * 新增人工充值
     * @param discountReq
     * @return
     */
    boolean save(DiscountReq discountReq);

    /**
     * 保存失败的数据
     * @param discountReq
     * @return
     */
    boolean saveFailedData(DiscountReq discountReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    DiscountReq getByReqId(Long id);
}
