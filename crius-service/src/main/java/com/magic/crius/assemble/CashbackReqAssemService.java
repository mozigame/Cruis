package com.magic.crius.assemble;

import com.magic.crius.vo.CashbackReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:46
 * 返水
 */
public interface CashbackReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(CashbackReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
