package com.magic.crius.assemble;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;

import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 * 用户充值成功
 */
public interface OnlChargeAssemService {

    /**
     * 处理在kafka中获取的对象
     * @param req
     */
    void procKafkaData(OnlChargeReq req);

    /**
     * 在redis中获取数据，然后进行清洗
     */
    boolean convertData(Date date);
}
