package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.CashbackReqAssemService;
import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.CashbackReqService;
import com.magic.crius.vo.CashbackReq;
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

    @Resource
    private CashbackReqService cashbackReqService;
    /*会员反水详情*/
    @Resource
    private OwnerReforwardDetailAssemService ownerReforwardDetailAssemService;
    /*账户交易明细*/
    @Resource
    private UserTradeAssemService userTradeAssemService;

    public void procKafkaData(CashbackReq req) {
        if (cashbackReqService.getByReqId(req.getReqId()) == null) {
            if (!cashbackReqService.save(req)) {
                //todo
            }
        }
    }


}
