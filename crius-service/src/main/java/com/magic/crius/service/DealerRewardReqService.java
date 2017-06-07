package com.magic.crius.service;

import com.magic.crius.vo.DealerRewardReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:34
 * 打赏
 */
public interface DealerRewardReqService {

    /**
     * @param req
     * @return
     */
    boolean save(DealerRewardReq req);

    /**
     * @param reqId
     * @return
     */
    DealerRewardReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的打赏信息
     * @param date
     * @return
     */
    List<DealerRewardReq> batchPopRedis(Date date);
}
