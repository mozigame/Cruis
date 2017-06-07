package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.CashbackReqAssemService;
import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.service.CashbackReqService;
import com.magic.crius.vo.CashbackReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:46
 */
@Service
public class CashbackReqAssemServiceImpl implements CashbackReqAssemService {

    @Resource
    private CashbackReqService cashbackReqService;
    /*反水详情*/
    @Resource
    private OwnerReforwardDetailAssemService ownerReforwardDetailAssemService;

    @Override
    public void procKafkaData(CashbackReq req) {
        if (cashbackReqService.getByReqId(req.getReqId()) == null) {
            if (!cashbackReqService.save(req)) {
                //todo
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<CashbackReq> list = cashbackReqService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            Map<String, OwnerReforwardDetail> ownerReforwardDetailHashMap = new HashMap<>();
            for (CashbackReq req : list) {
                if (ownerReforwardDetailHashMap.get(req.getUserId() + "_" + req.getGameHallId()) == null) {
                    OwnerReforwardDetail summmary = new OwnerReforwardDetail();
                    summmary.setOwnerId(req.getOwnerId());
                    summmary.setUserId(req.getUserId());
                    summmary.setGameType(String.valueOf(req.getGameHallId()));
                    //TODO 等待获取
                    summmary.setOrderNum(0);
                    summmary.setOrderCount(req.getBettAmount());
                    summmary.setEffectOrderCount(req.getVaildBettAmount());
                    summmary.setReforwardMoneyCount(req.getAmount());
                    //TODO 返水编号
                    summmary.setOrderId(0L);
                    summmary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    summmary.setCreateTime(req.getProduceTime());
                    summmary.setUpdateTime(req.getProduceTime());
                    ownerReforwardDetailHashMap.put(req.getUserId() + "_" + req.getGameHallId(), summmary);
                } else {
                    OwnerReforwardDetail summmary = ownerReforwardDetailHashMap.get(req.getUserId() + "_" + req.getGameHallId());
                    //todo 待确定
                    summmary.setOrderNum(summmary.getOrderNum());
                    summmary.setOrderCount(summmary.getOrderCount() + req.getBettAmount());
                    summmary.setEffectOrderCount(summmary.getEffectOrderCount() + req.getVaildBettAmount());
                    summmary.setReforwardMoneyCount(summmary.getReforwardMoneyCount() + req.getAmount());
                }

            }
            if (ownerReforwardDetailHashMap.size() > 0) {
                ownerReforwardDetailAssemService.batchSave(ownerReforwardDetailHashMap);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
