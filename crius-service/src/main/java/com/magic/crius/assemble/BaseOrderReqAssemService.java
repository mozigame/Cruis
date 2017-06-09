package com.magic.crius.assemble;

import com.magic.crius.vo.BaseOrderReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:16
 */
public interface BaseOrderReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(BaseOrderReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
