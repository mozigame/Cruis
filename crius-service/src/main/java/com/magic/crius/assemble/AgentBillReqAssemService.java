package com.magic.crius.assemble;

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

    private void procKafkaData(AgentBillReq req) {


        ProxyBillDetail detail = new ProxyBillDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setProxyId(req.getAgentId());
        //Todo 获取不到代理账号
//        detail.setProxyName();
        //todo 类型不确定
        detail.setOrderId(req.getBillId());
        //todo 如何获取pdate
//        detail.setPdate();
        //todo 如何获取
//        detail.setUserNum();
        detail.setIncome(req.getPayoffTotalAmount());
//        detail.setEffectOrderCount();


//        private Long ownerId;
//        private Long proxyId;   //代理id
//        private String proxyName;   //代理名称
//        private String orderId; //账单编号
//        private String pdate;//统计期数
//        private Long userNum;//会员数量
//        private Long income;//本期收益
//        private Long effectOrderCount;//当期有效投注
//        private Long cost;//当期累计费用
//        private Long reforwardAccount;//可获退佣
//        private Long recordReforwardAccount;//已获退佣
//        private Integer reforwardState;//退佣状态

    }

}
