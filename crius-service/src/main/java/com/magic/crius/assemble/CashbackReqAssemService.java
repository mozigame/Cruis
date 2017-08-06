package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.CashbackReqAssemService;
import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.CashbackReqService;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.CashbackReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:46
 */
@Service
public class CashbackReqAssemService {


    private static final Logger logger = Logger.getLogger(CashbackReqAssemService.class);

    @Resource
    private CashbackReqService cashbackReqService;

    public void procKafkaData(CashbackReq req) {
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkMongoResId()) {
                logger.info("save CashbackReq checkReqId : "+ req.getReqId());
                if (cashbackReqService.getByReqId(req.getReqId()) == null) {
                    if (!cashbackReqService.save(req)) {
                        logger.error("save CashbackReq error,reqId : " + req.getReqId());
                    }
                }
            } else {
                if (!cashbackReqService.save(req)) {
                    logger.error("save CashbackReq error,reqId : " + req.getReqId());
                }
            }

        } else {
            logger.warn("reqId is null,"+ JSON.toJSONString(req));
        }
    }


}
