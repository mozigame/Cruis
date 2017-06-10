package com.magic.crius.assemble.impl;

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
public class CashbackReqAssemServiceImpl implements CashbackReqAssemService {

    @Resource
    private CashbackReqService cashbackReqService;
    /*会员反水详情*/
    @Resource
    private OwnerReforwardDetailAssemService ownerReforwardDetailAssemService;
    /*账户交易明细*/
    @Resource
    private UserTradeAssemService userTradeAssemService;

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
            List<UserTrade> userTrades = new ArrayList<>();
            Set<CashbackReq> successReqs = new HashSet<>();
            for (CashbackReq req : list) {
                /*反水详情*/
                ownerReforwardDetailHashMap.add(assembleOwnerReforwardDetail(req));
                /*账户交易明细*/
                userTrades.add(assembleUserTrade(req));

                /*成功的数据*/
                CashbackReq sucReq = new CashbackReq();
                sucReq.setReqId(req.getReqId());
                sucReq.setProduceTime(req.getProduceTime());
                successReqs.add(sucReq);
            }

            ownerReforwardDetailAssemService.batchSave(ownerReforwardDetailHashMap);
            userTradeAssemService.batchSave(userTrades);

            /*mongo添加处理成功的数据*/
            if (!cashbackReqService.saveSuc(successReqs)) {
                //todo
            }
            return true;
        }
        return false;
    }

    private OwnerReforwardDetail assembleOwnerReforwardDetail(CashbackReq req) {
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
        return summmary;
    }

    private UserTrade assembleUserTrade(CashbackReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(0);
        //todo 存取类型
        userTrade.setActiontype(0);
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }
}
