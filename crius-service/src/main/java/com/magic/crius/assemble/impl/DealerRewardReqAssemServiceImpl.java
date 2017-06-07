package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.DealerRewardReqAssemService;
import com.magic.crius.assemble.OwnerAwardDetailAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.vo.DealerRewardReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:40
 */
@Service
public class DealerRewardReqAssemServiceImpl implements DealerRewardReqAssemService {

    @Resource
    private DealerRewardReqService dealerRewardReqService;
    /*打赏明细*/
    @Resource
    private OwnerAwardDetailAssemService ownerAwardDetailAssemService;

    @Override
    public void procKafkaData(DealerRewardReq req) {
        if (dealerRewardReqService.getByReqId(req.getReqId()) == null) {
            if (!dealerRewardReqService.save(req)) {
                //todo
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<DealerRewardReq> list = dealerRewardReqService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<OwnerAwardDetail> details = new ArrayList<>();
            for (DealerRewardReq req : list) {
                OwnerAwardDetail detail = new OwnerAwardDetail();
                detail.setOwnerId(req.getOwnerId());
                detail.setUserId(req.getUserId());
                detail.setGameId(String.valueOf(req.getGameId()));
                detail.setOrderId(req.getBillId());
                //TODO 待确定
                detail.setCurrencyCode("");
                //todo 待确定
                detail.setCurrencyName("");
                //Todo 待确定 游戏桌号是局号
                detail.setOfficeNum(req.getGameDeskNum().intValue());
                detail.setOrderCount(req.getRewardAmount());
                detail.setDealerCode(String.valueOf(req.getDealerId()));
                detail.setDealerName(req.getDealerName());
                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                detail.setCreateTime(req.getProduceTime());
                details.add(detail);
            }
            if (details.size() > 0) {
                ownerAwardDetailAssemService.batchSave(details);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
