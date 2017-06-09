package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.CashbackReqAssemService;
import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.assemble.OwnerReforwardMoneyToGameAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.po.OwnerReforwardMoneyToGame;
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
public class CashbackReqAssemServiceImpl implements CashbackReqAssemService {

    @Resource
    private CashbackReqService cashbackReqService;
    /*会员反水详情*/
    @Resource
    private OwnerReforwardDetailAssemService ownerReforwardDetailAssemService;
    /*业主返水详情汇总*/
    @Resource
    private OwnerReforwardMoneyToGameAssemService ownerReforwardMoneyToGameAssemService;

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
            List<OwnerReforwardDetail> ownerReforwardDetailHashMap = new ArrayList<>();
            Map<String, OwnerReforwardMoneyToGame> ownerReforwardMoneyToGameMap = new HashMap<>();
            Set<CashbackReq> successReqs = new HashSet<>();
            for (CashbackReq req : list) {
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
                ownerReforwardDetailHashMap.add(summmary);

                if (ownerReforwardMoneyToGameMap.get(req.getOwnerId() + "_" + req.getGameHallId()) == null) {
                    OwnerReforwardMoneyToGame moneyToGame = new OwnerReforwardMoneyToGame();
                    moneyToGame.setOwnerId(req.getOwnerId());
                    moneyToGame.setGameType(req.getGameHallId() + "");
                    //todo 等待获取
                    moneyToGame.setOrderNum(0);
                    moneyToGame.setOrderCount(req.getBettAmount());
                    moneyToGame.setEffectOrderCount(req.getVaildBettAmount());
                    moneyToGame.setReforwardMoneyCount(req.getAmount());
                    moneyToGame.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    ownerReforwardMoneyToGameMap.put(req.getOwnerId() + "_" + req.getGameHallId(), moneyToGame);
                } else {
                    OwnerReforwardMoneyToGame moneyToGame = ownerReforwardMoneyToGameMap.get(req.getOwnerId() + "_" + req.getGameHallId());
                    //todo 等待获取
                    moneyToGame.setOrderNum(0);
                    moneyToGame.setOrderCount(moneyToGame.getOrderCount() + req.getBettAmount());
                    moneyToGame.setEffectOrderCount(moneyToGame.getEffectOrderCount() + req.getVaildBettAmount());
                    moneyToGame.setReforwardMoneyCount(moneyToGame.getReforwardMoneyCount() + req.getAmount());
                }
                /*成功的数据*/
                CashbackReq sucReq = new CashbackReq();
                sucReq.setReqId(req.getReqId());
                sucReq.setProduceTime(req.getProduceTime());
                successReqs.add(sucReq);
            }

            if (ownerReforwardDetailHashMap.size() > 0) {
                ownerReforwardDetailAssemService.batchSave(ownerReforwardDetailHashMap);
            }
            if (ownerReforwardMoneyToGameMap.size() > 0) {
                ownerReforwardMoneyToGameAssemService.batchSave(ownerReforwardMoneyToGameMap);
            }
            /*mongo添加处理成功的数据*/
            if (!cashbackReqService.saveSuc(successReqs)) {
                //todo
            }
            return true;
        }
        return false;
    }
}
