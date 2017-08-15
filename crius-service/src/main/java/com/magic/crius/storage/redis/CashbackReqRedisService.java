package com.magic.crius.storage.redis;

import com.magic.crius.vo.CashbackReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:41
 */
public interface CashbackReqRedisService{

    /**
     * @param req
     * @return
     */
    boolean save(CashbackReq req);

    /**
     * 批量获取返水成功信息
     * @param date
     * @return
     */
    List<CashbackReq> batchPop(Date date);


}
