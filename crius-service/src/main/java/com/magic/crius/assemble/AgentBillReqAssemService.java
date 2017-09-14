package com.magic.crius.assemble;

import com.magic.crius.enums.BillType;
import com.magic.crius.po.BillInfo;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.vo.AgentBillReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 16:04
 * 代理退佣信息
 */
@Service
public class AgentBillReqAssemService {


    @Resource
    private ProxyBillDetailAssemService proxyBillDetailAssemService;
    @Resource
    private BillInfoAssemService billInfoAssemService;

//    private void procKafkaData(AgentBillReq req) {
//
//        billInfoAssemService.save(assemBillInfo(req));
//
//        ProxyBillDetail detail = new ProxyBillDetail();
//        detail.setOwnerId(req.getOwnerId());
//        detail.setProxyId(req.getAgentId());
//        //Todo 获取不到代理账号
////        detail.setProxyName();
//        detail.setOrderId(req.getBillId());
//        //todo 如何获取pdate
////        detail.setPdate();
//        //todo 如何获取
////        detail.setUserNum();
//        detail.setIncome(req.getPayoffTotalAmount());
////        detail.setEffectOrderCount();
//
//
////        private Long ownerId;
////        private Long proxyId;   //代理id
////        private String proxyName;   //代理名称
////        private String orderId; //账单编号
////        private String pdate;//统计期数
////        private Long userNum;//会员数量
////        private Long income;//本期收益
////        private Long effectOrderCount;//当期有效投注
////        private Long cost;//当期累计费用
////        private Long reforwardAccount;//可获退佣
////        private Long recordReforwardAccount;//已获退佣
////        private Integer reforwardState;//退佣状态
//
//    }

    /*账单汇总表*/
    private BillInfo assemBillInfo(AgentBillReq req) {
        BillInfo info = new BillInfo();
        info.setIncome(req.getOwnerId());
        info.setOrderId(req.getBillId() + "");
        //todo  账单名称 dubbo 获取?
//        info.setOrderName();
        info.setPdate(Integer.parseInt(req.getBillDate()));
        info.setSchemeCode(req.getSchemeId() + "");
        info.setSchemeName(req.getSchemeName());
        //todo 后台处理
//        info.setAccount(req.getTotalCost());
        info.setIncome(req.getCostTotalAmount());
        info.setBillType(BillType.proxy.value());
        info.setStartTime(req.getBillStartTime());
        info.setEndTime(req.getBillEndTime());
        //todo 通过dubbo 获取
//        info.setPdateName();
        info.setStatus(0);
        return info;
    }

}
