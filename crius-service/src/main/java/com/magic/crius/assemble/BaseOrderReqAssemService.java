package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:20
 */
@Service
public class BaseOrderReqAssemService {

    @Resource
    private BaseOrderReqService baseOrderReqService;

    public void procKafkaData(BaseOrderReq req) {
        if (baseOrderReqService.getByReqId(req.getReqId()) == null) {
            if (!baseOrderReqService.save(req)) {
                //todo
            }
        }
    }

}
