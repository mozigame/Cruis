package com.magic.crius.assemble;

import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.vo.DealerRewardReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:40
 */
@Service
public class DealerRewardReqAssemService  {

    @Resource
    private DealerRewardReqService dealerRewardReqService;
    /*打赏明细*/
    @Resource
    private OwnerAwardDetailAssemService ownerAwardDetailAssemService;

    public void procKafkaData(DealerRewardReq req) {
        if (dealerRewardReqService.getByReqId(req.getReqId()) == null) {
            if (!dealerRewardReqService.save(req)) {
                //todo
            }
        }
    }


}
