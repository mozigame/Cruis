package com.magic.crius.storage.redis;

import com.magic.crius.vo.JpReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:46
 * 彩金明细
 */
public interface JpReqRedisService {

    /**
     * @param req
     * @return
     */
    boolean save(JpReq req);

    /**
     * 批量获取人工提现成功信息
     * @param date
     * @return
     */
    List<JpReq> batchPop(Date date);
}
