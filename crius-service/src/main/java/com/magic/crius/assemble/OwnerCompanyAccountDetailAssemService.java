package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.enums.SummaryKind;
import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.service.OwnerCompanyAccountDetailService;
import com.magic.crius.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:52
 * 公司账目汇总
 */
@Service("ownerCompanyAccountDetailAssemService")
public class OwnerCompanyAccountDetailAssemService {

    @Resource
    private OwnerCompanyAccountDetailService ownerCompanyAccountDetailService;

    public void batchSave(List<OwnerCompanyAccountDetail> ownerCompanyAccountSummmaries) {

        //todo 错误处理
        if (ownerCompanyAccountSummmaries.size() > 0) {
            boolean saveResult = ownerCompanyAccountDetailService.batchInsert(ownerCompanyAccountSummmaries);
        }

    }

    /**
     * 公司入款
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(PreCmpChargeReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getChargeAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_CMP_CHARGE.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_CMP_CHARGE.typeName());
        account.setSummaryKind(SummaryKind.income.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }

    /**
     * 人工充值
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(OperateChargeReq req,Long userId) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(userId);
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getChargeAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_OPR_CHARGE.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_OPR_CHARGE.typeName());
        account.setSummaryKind(SummaryKind.income.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }


    /**
     * 用户充值(在线充值)成功
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(OnlChargeReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getChargeAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_ONL_CHARGE.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_ONL_CHARGE.typeName());
        account.setSummaryKind(SummaryKind.income.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }


    /**
     * 返水
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(CashbackReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_CAHSBACK.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_CAHSBACK.typeName());
        account.setSummaryKind(SummaryKind.outlay.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }


    /**
     * 优惠
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(DiscountReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getOfferAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_DISCOUNT.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_DISCOUNT.typeName());
        account.setSummaryKind(SummaryKind.outlay.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }


    /**
     * 人工提现
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(OperateWithDrawReq req,Long userId) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(userId);
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_OPR_WITHDRAW.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_OPR_WITHDRAW.typeName());
        account.setSummaryKind(SummaryKind.outlay.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }


    /**
     * 用户提现
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail(PreWithdrawReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getRealWithdrawAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.PLUTUS_USER_WITHDRAW.type());
        account.setSummaryTypeName(KafkaConf.DataType.PLUTUS_USER_WITHDRAW.typeName());
        account.setSummaryKind(SummaryKind.outlay.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }
    
    
    /**
     * 会员扣款
     * @param req
     * @return
     */
    public OwnerCompanyAccountDetail assembleOwnerCompanyAccountDetail4TaxCount(PreWithdrawReq req) {
        OwnerCompanyAccountDetail account = new OwnerCompanyAccountDetail();
        account.setUserId(req.getUserId());
        account.setOwnerId(req.getOwnerId());
        account.setSummaryMoneyCount(req.getNeedPayAmount());
        account.setSummaryUserNum(1);
        //TODO 此处待确定
        account.setSummaryType(KafkaConf.DataType.TAX_COUNT.type());
        account.setSummaryTypeName(KafkaConf.DataType.TAX_COUNT.typeName());
        account.setSummaryKind(SummaryKind.income.value());
        account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return account;
    }

	

}
