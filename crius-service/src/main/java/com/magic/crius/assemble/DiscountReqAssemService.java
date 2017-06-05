package com.magic.crius.assemble;

import com.magic.crius.vo.DiscountReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:39
 * 优惠赠送
 */
public interface DiscountReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void procKafkaData(DiscountReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);

}
