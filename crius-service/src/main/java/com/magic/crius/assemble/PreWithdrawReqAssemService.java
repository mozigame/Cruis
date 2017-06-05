package com.magic.crius.assemble;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreWithdrawReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:31
 * 用户提现
 */
public interface PreWithdrawReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void procKafkaData(PreWithdrawReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
