package com.magic.crius.storage.redis;

import com.magic.crius.vo.OperateWithDrawReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:42
 * 人工提现
 */
public interface OperateWithDrawReqRedisService {
    /**
     * @param req
     * @return
     */
    boolean save(OperateWithDrawReq req);

    /**
     * 批量获取人工提现成功信息
     * @param date
     * @return
     */
    List<OperateWithDrawReq> batchPop(Date date);

}
