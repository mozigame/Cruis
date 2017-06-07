package com.magic.crius.assemble;

import com.magic.crius.vo.OperateWithDrawReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:48
 * 人工提现
 */
public interface OperateWithDrawReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(OperateWithDrawReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
