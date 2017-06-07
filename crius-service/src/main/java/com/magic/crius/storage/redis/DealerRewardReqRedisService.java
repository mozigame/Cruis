package com.magic.crius.storage.redis;

import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DealerRewardReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:56
 * 打赏
 */
public interface DealerRewardReqRedisService {

    /**
     * @param req
     * @return
     */
    boolean save(DealerRewardReq req);

    /**
     * 批量获取打赏信息
     * @param date
     * @return
     */
    List<DealerRewardReq> batchPop(Date date);
}
