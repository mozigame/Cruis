package com.magic.crius.assemble;

import com.magic.crius.enums.BillType;
import com.magic.crius.po.BillInfo;
import com.magic.crius.po.GameInfo;
import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.OwnerHallBillVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 16:03
 * 业主包网费用
 */
@Service
public class OwnerBillReqAssemService {

    @Resource
    private GameInfoService gameInfoService;
    @Resource
    private OwnerBillSummary2gameAssemService ownerBillSummary2gameAssemService;
    @Resource
    private OwnerBillSummary2costAssemService ownerBillSummary2costAssemService;
    @Resource
    private BillInfoAssemService billInfoAssemService;

    public void procKafkaData(OwnerBillReq req) {

        /*账单汇总*/
        billInfoAssemService.save(assemBillInfo(req));

        /*业主成本部分退佣汇总*/
        if (req.getOwnerCostInfo() != null && req.getOwnerCostInfo().size() > 0) {
            List<OwnerBillSummary2cost> costs = new ArrayList<>();
            for (AmountVo vo : req.getOwnerCostInfo()) {
                costs.add(assemSummaryCost(req, vo));
            }
            ownerBillSummary2costAssemService.batchInsert(costs);

        }
        /*业主月游戏账单汇总*/
        if (req.getHallBillInfos() != null && req.getHallBillInfos().size() > 0) {
            List<OwnerBillSummary2game> games = new ArrayList<>();
            for (OwnerHallBillVo vo : req.getHallBillInfos()) {
                games.add(assemSummaryGame(req, vo));
            }
            ownerBillSummary2gameAssemService.batchInsert(games);
        }
    }

    /*账单汇总表*/
    private BillInfo assemBillInfo(OwnerBillReq req) {
        BillInfo info = new BillInfo();
        info.setIncome(req.getOwnerId());
        info.setOrderId(req.getBillId() + "");
        //todo  账单名称 dubbo 获取?
//        info.setOrderName();
        info.setPdate(Integer.parseInt(req.getBillDate()));
        info.setSchemeCode(req.getSchemeId() + "");
        info.setSchemeName(req.getSchemeName());
        info.setAccount(req.getTotalCost());
        info.setIncome(req.getRealToalCost());
        info.setBillType(BillType.owner.value());
        info.setStartTime(req.getBillStartTime());
        info.setEndTime(req.getBillEndTime());
        //todo 通过dubbo 获取
//        info.setPdateName();
        info.setStatus(0);
        return info;
    }

    /*业主成本部分退佣汇总*/
    private OwnerBillSummary2cost assemSummaryCost(OwnerBillReq req, AmountVo vo) {
        OwnerBillSummary2cost cost = new OwnerBillSummary2cost();
        cost.setOwnerId(req.getOwnerId());
        cost.setOrderId(req.getBillId() + "");
        cost.setBillType(cost.getBillType());
        cost.setBillTypeName(cost.getBillTypeName());
        //todo 待确定
        cost.setBill(req.getRealToalCost());
        //todo 待确定
        cost.setCost(vo.getAmount());
        //todo 待确定
        cost.setRemark("");
        return cost;
    }

    /*业主月游戏账单汇总*/
    private OwnerBillSummary2game assemSummaryGame(OwnerBillReq req, OwnerHallBillVo vo) {
        OwnerBillSummary2game game = new OwnerBillSummary2game();
        game.setOwnerId(req.getOwnerId());
        game.setOrderId(req.getBillId() + "");
        //todo 如何获取 统计期数
        game.setPdate(req.getBillDate());
        //todo
        game.setPdateName(req.getSchemeName());
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameFactoryType(vo.getPlatformId()+"");
        gameInfo.setGameAbstractType(vo.getHallTypeId()+"");
        gameInfo = gameInfoService.get(gameInfo);
        game.setGameType(gameInfo.getGameType());
        game.setIncome(vo.getPayOffAmount());
        game.setBillCount(vo.getNeedPayAmount());
        return game;
    }
}
