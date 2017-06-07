package com.magic.crius.assemble;

import com.magic.crius.vo.DealerRewardReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:39
 * 打赏
 */
public interface DealerRewardReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(DealerRewardReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
