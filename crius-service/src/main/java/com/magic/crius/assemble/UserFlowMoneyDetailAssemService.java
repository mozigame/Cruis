package com.magic.crius.assemble;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.magic.analysis.enums.SummaryType;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.analysis.enums.ActionType;
import com.magic.analysis.enums.PayMethod;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.service.UserFlowMoneyDetailService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:11
 */
@Service
public class UserFlowMoneyDetailAssemService {

    private static final Logger logger = Logger.getLogger(UserFlowMoneyDetailAssemService.class);


    @Resource
    private UserFlowMoneyDetailService userFlowMoneyDetailService;

    public boolean batchSave(List<UserFlowMoneyDetail> details) {
        return userFlowMoneyDetailService.batchSave(details);
    }

    public UserFlowMoneyDetail assembleUserFlowMoneyDetail(OnlChargeReq req) {
        UserFlowMoneyDetail detail = new UserFlowMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        //用户充值填写商户信息
        detail.setMerchantCode(req.getMerchantCode()+"");
        detail.setMerchantName(req.getMerchantName());
        detail.setOrderCount(req.getChargeAmount());
        //Todo 待确定
        detail.setState(0);
        //todo kevin 提供
        detail.setPayMethod(req.getChargeType());
        detail.setFlowId(req.getBillId());
        //TODO 待确定
        detail.setFlowType(ActionType.CHONG_ZHI.getStatus());
        detail.setOrderId(req.getOrderId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        detail.setRemark(req.getRemark());

        //todo kevin 提供,公司入款、线上入款自己定义，人工入款由kevin提供
        detail.setFlowDetailType(SummaryType.ONLINE_CHARGE.getStatus());


        //
        disposeHandlerId(req, detail);

        if (StringUtils.isNotBlank(req.getHandlerName())){
            detail.setHandlerName(req.getHandlerName());
        }

        if (req.getPaySystemCode()!=null){
            detail.setPaySystemCode(req.getPaySystemCode());
        }

        if (StringUtils.isNotBlank(req.getPaySystemName())){
            detail.setPaySystemName(req.getPaySystemName());
        }
        if (logger.isDebugEnabled()){
            logger.debug("assembleUserFlowMoneyDetail::detail = " + detail
                + ", req = " + req);
        }
        return detail;
    }

    private void disposeHandlerId(OnlChargeReq req, UserFlowMoneyDetail detail) {
        try{
            if (StringUtils.isNotBlank(req.getHandlerId())){
                detail.setHandlerId(Long.parseLong(req.getHandlerId()));
            }
        }catch (Exception e){
            logger.error("disposeHandlerId::error::req = " + req.getHandlerId(),e);
        }

    }

    public UserFlowMoneyDetail assembleUserFlowMoneyDetail(PreCmpChargeReq req) {
        UserFlowMoneyDetail detail = new UserFlowMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setMerchantCode(req.getBankNum());
        //公司入款填写银行信息
        detail.setMerchantName(req.getBankCode());
        detail.setOrderCount(req.getChargeAmount());
        //Todo 待确定
        detail.setState(1);
        //todo kevin 提供
        detail.setPayMethod(req.getChargeType());
        detail.setFlowId(req.getBillId());
        //TODO 待确定
        detail.setFlowType(ActionType.CHONG_ZHI.getStatus());
        detail.setOrderId(req.getBillId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //审核人名称
        detail.setHandlerId(req.getHandlerId());
        detail.setHandlerName(req.getHandlerName());
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());

        //todo kevin 提供,公司入款、线上入款自己定义，人工入款由kevin提供
        detail.setFlowDetailType(SummaryType.COMPANY_INCOME.getStatus());
        return detail;
    }

    public UserFlowMoneyDetail assembleUserFlowMoneyDetail(OperateChargeReq req,Long userId, Long billId) {
        UserFlowMoneyDetail detail = new UserFlowMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(userId);

        detail.setOrderCount(req.getChargeAmount());
        //Todo 待确定
        detail.setState(1);
        //todo kevin 提供
        detail.setPayMethod(PayMethod.ANOTHER.getMethod());
        detail.setFlowId(req.getReqId());
        //TODO 待确定
        detail.setFlowType(ActionType.CHONG_ZHI.getStatus());
        detail.setOrderId(billId);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //审核人名称
        detail.setHandlerId(req.getHandlerId());
        detail.setHandlerName(req.getHandlerName());
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        // todo 新加type，由kevin提供
        detail.setFlowDetailType(req.getType() == null ? 0 : req.getType());
        return detail;
    }
}
