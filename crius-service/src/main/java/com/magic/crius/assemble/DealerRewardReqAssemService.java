package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.DealerRewardReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:40
 */
@Service
public class DealerRewardReqAssemService  {

    private static final Logger logger = Logger.getLogger(DealerRewardReqAssemService.class);

    @Resource
    private DealerRewardReqService dealerRewardReqService;
    /*打赏明细*/
    @Resource
    private OwnerAwardDetailAssemService ownerAwardDetailAssemService;

    public void procKafkaData(DealerRewardReq req) {
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkMongoResId()) {
                if (dealerRewardReqService.getByReqId(req.getReqId()) == null) {
                    if (!dealerRewardReqService.save(req)) {
                        logger.error("save DealerRewardReq error,reqId : " + req.getReqId());
                    }
                }else {
                    logger.warn("save DealerRewardReq failed, reqId has exist, reqId : "+ req.getReqId());
                }
            } else {
                if (!dealerRewardReqService.save(req)) {
                    logger.error("save DealerRewardReq error,reqId : " + req.getReqId());
                }
            }
        } else {
            logger.warn("reqId is null"+ JSON.toJSONString(req));
        }
    }


}
