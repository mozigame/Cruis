package com.magic.crius.assemble;

import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:32
 */
public interface PreCmpChargeAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(PreCmpChargeReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
