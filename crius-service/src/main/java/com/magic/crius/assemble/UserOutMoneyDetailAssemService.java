package com.magic.crius.assemble;

import com.magic.analysis.enums.SummaryType;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.service.UserOutMoneyDetailService;
import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:45
 * 会员出款明细
 */
@Service
public class UserOutMoneyDetailAssemService {

    @Resource
    private UserOutMoneyDetailService userOutMoneyDetailService;

    public void batchSave(List<UserOutMoneyDetail> details) {
        userOutMoneyDetailService.batchInsert(details);
    }

    /**
     * 人工提现组装会员出款明细
     * @param req
     * @param userId
     * @param billId
     * @return
     */
    public UserOutMoneyDetail assembleUserOutMoneyDetail(OperateWithDrawReq req, Long userId, Long billId) {
        UserOutMoneyDetail detail = new UserOutMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(userId);
        detail.setOrderCount(req.getAmount());

        //TODO 待定
        detail.setState(1);
        detail.setOrderId(billId);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setHandlerId(req.getHandlerId());
        detail.setHandlerName(req.getHandlerName());
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        //会员出款扣款
        detail.setCostAmount(0L);
        detail.setFeeAmount(0L);
        detail.setOfferAmount(0L);

        //todo 人工出款由kevin提供
        detail.setOutDetailType(req.getWithdrawType()==null ? 0 : req.getWithdrawType());
        return detail;
    }

    /**
     * 会员出款组装会员出款明细
     * @param req
     * @return
     */
    public UserOutMoneyDetail assembleUserOutMoneyDetail(PreWithdrawReq req) {
        UserOutMoneyDetail detail = new UserOutMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setOrderCount(req.getRealWithdrawAmount());
        //TODO kevin提供
        detail.setTaxCount(req.getNeedPayAmount());
        //TODO 待定
        detail.setState(0);
        detail.setOrderId(req.getBillId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setHandlerId(req.getHandlerId());
        detail.setHandlerName(req.getHandlerName());
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());

        //会员出款扣款
        detail.setCostAmount(req.getCostAmount() == null ? 0: req.getCostAmount());
        detail.setFeeAmount(req.getFeeAmount() == null ? 0: req.getFeeAmount());
        detail.setOfferAmount(req.getOfferAmount() == null ? 0: req.getOfferAmount());

        //todo kevin 提供,会员出款自己定义，人工出款由kevin提供
        detail.setOutDetailType(SummaryType.USER_OUT_MONEY.getStatus());
        return detail;
    }
}
