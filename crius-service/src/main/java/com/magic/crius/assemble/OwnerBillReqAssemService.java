package com.magic.crius.assemble;

import com.magic.crius.enums.BillType;
import com.magic.crius.po.BillInfo;
import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.OwnerHallBillVo;
import org.springframework.stereotype.Service;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 16:03
 * 业主包网费用
 */
@Service
public class OwnerBillReqAssemService {


    public void procKafkaData(OwnerBillReq req) {

        int pdate = Integer.parseInt(req.getBillDate());

        BillInfo info = new BillInfo();
        info.setIncome(req.getOwnerId());
        info.setOrderId(req.getBillId()+"");
        //todo  账单名称 dubbo 获取?
//        info.setOrderName();
        info.setPdate(pdate);
        info.setSchemeCode(req.getSchemeId()+"");
        info.setSchemeName(req.getSchemeName());
        info.setAccount(req.getTotalCost());
        info.setIncome(req.getRealToalCost());
        info.setBillType(BillType.owner.value());
        info.setStartTime(req.getBillStartTime());
        info.setEndTime(req.getBillEndTime());
        //todo 通过dubbo 获取
//        info.setPdateName();
        info.setStatus(0);

        if (req.getOwnerCostInfo() != null && req.getOwnerCostInfo().size() > 0) {
            for (AmountVo vo : req.getOwnerCostInfo()) {
                OwnerBillSummary2cost cost = new OwnerBillSummary2cost();
                cost.setOwnerId(req.getOwnerId());
                cost.setOrderId(req.getBillId() + "");
                cost.setBillType(cost.getBillType());
                cost.setBillTypeName(cost.getBillTypeName());
                cost.setBill(req.getRealToalCost());
                //todo 待确定
                cost.setRemark("");
            }
        }

        if (req.getHallBillInfos() != null && req.getHallBillInfos().size() > 0) {
            for (OwnerHallBillVo vo : req.getHallBillInfos()) {
                OwnerBillSummary2game game = new OwnerBillSummary2game();
                game.setOwnerId(req.getOwnerId());
                game.setOrderId(req.getBillId() + "");
                //todo 如何获取 统计期数
                game.setPdate(req.getBillDate());
                //todo
                game.setPdateName(req.getSchemeName());

            }
        }





    }
}
