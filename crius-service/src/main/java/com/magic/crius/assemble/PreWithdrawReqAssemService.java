package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.vo.PreWithdrawReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:32
 * 用户提现
 */
@Service
public class PreWithdrawReqAssemService {

    private static final Logger logger = Logger.getLogger(PreWithdrawReqAssemService.class);
    /**
     * 用户提现
     */
    @Resource
    private PreWithdrawReqService preWithdrawService;

    public void procKafkaData(PreWithdrawReq req) {
        if (req.getReqId() != null && preWithdrawService.getByReqId(req.getReqId()) == null) {
            if (!preWithdrawService.save(req)) {
                logger.error("save PreWithdrawReq error,reqId : " + req.getReqId());
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
