package com.magic.crius.assemble;

import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:46
 * 人工充值
 */
public interface OperateChargeReqAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void  procKafkaData(OperateChargeReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);

}
