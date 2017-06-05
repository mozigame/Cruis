package com.magic.crius.storage.mongo;

import com.magic.crius.vo.PreWithdrawReq;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:14
 */
public interface PreWithdrawReqMongoService {

    /**
     * 新增用户提现
     * @param preWithdrawReq
     * @return
     */
    boolean save(PreWithdrawReq preWithdrawReq);

    /**
     * 保存失败的数据
     * @param preWithdrawReq
     * @return
     */
    boolean saveFailedData(PreWithdrawReq preWithdrawReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    PreWithdrawReq getByReqId(Long id);
}
