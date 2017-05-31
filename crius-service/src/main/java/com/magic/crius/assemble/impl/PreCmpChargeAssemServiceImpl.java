package com.magic.crius.assemble.impl;

import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.PreCmpChargeAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.service.PreCmpChargeService;
import com.magic.crius.storage.redis.PreCmpChargeRedisService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:32
 */
@Service("preCmpChargeAssembleService")
public class PreCmpChargeAssemServiceImpl implements PreCmpChargeAssemService {

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    @Resource
    private PreCmpChargeService preCmpChargeService;
    @Resource
    private PreCmpChargeRedisService preCmpChargeRedisService;

    @Override
    public void procPreCmpCharge(PreCmpChargeReq req) {
        if (preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                CriusLog.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public void convertData(Date date) {

        while (true) {

        }
    }
}
