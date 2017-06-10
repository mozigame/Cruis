package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.JpReqAssemService;
import com.magic.crius.assemble.PrizeDetailAssemService;
import com.magic.crius.po.PrizeDetail;
import com.magic.crius.service.JpReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.JpReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:50
 */
@Service
public class JpReqAssemServiceImpl implements JpReqAssemService {


    @Resource
    private JpReqService jpReqService;
    @Resource
    private PrizeDetailAssemService prizeDetailAssemService;

    @Override
    public void procKafkaData(JpReq req) {
        if (jpReqService.getByReqId(req.getReqId()) == null) {
            if (!jpReqService.save(req)) {
                CriusLog.error("save JpReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<JpReq> list = jpReqService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<PrizeDetail> prizeDetails = new ArrayList<>();
            for (JpReq req : list) {
                /*彩金明细*/
                PrizeDetail detail = new PrizeDetail();
                detail.setOwnerId(req.getOwnerId());
                detail.setUserId(req.getUserId());
                detail.setOrderId(req.getBillId());
                detail.setPrizeType(req.getJpType());
                //todo 待确定
                detail.setPrizeTypeName("");
                detail.setGameId(req.getGameId() + "");
                detail.setOrderCount(req.getJpAmount());
                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                detail.setCreateTime(req.getProduceTime());
                prizeDetails.add(detail);
            }
            if (prizeDetails.size() > 0) {
                prizeDetailAssemService.batchSave(prizeDetails);
            }
        }
        return true;
    }
}
