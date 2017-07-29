package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:56
 * 人工充值/人工入款（成功）
 */
public class OperateChargeReq {

    @JSONField(name = "BillIds")
    private Long billIds[];
    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "UserIds")
    private Long userIds[];//用户Ids，数组
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "ChargeAmount")
    private Long chargeAmount;//人工充值金额
    @JSONField(name = "Currency")
    private String currency;
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "DepositOffer")
    private Long depositOffer;// 存款优惠
    @JSONField(name = "RemittanceOffer")
    private Long remittanceOffer;// 汇款优惠
    @JSONField(name = "HandlerId")
    private Long handlerId;//财务操作人员Id
    @JSONField(name = "HandlerName")
    private String handlerName;//财务操作人员名字
    @JSONField(name = "Type")
    private Integer type;
    @JSONField(name = "Remark")
    private String remark;
    @JSONField(name = "ProduceTime")
    private Long produceTime;//注入kafka的ms时间

    @JSONField(name = "Balance")
    private Long balance;   //余额

    @JSONField(name = "DepRemark")
    private String DepRemark;//存款优惠备注

    @JSONField(name = "RemRemark")
    private String RemRemark ;//汇款优惠备注

    public String getDepRemark() {
        return DepRemark;
    }

    public void setDepRemark(String depRemark) {
        DepRemark = depRemark;
    }

    public String getRemRemark() {
        return RemRemark;
    }

    public void setRemRemark(String remRemark) {
        RemRemark = remRemark;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getDepositOffer() {
        return depositOffer;
    }

    public void setDepositOffer(Long depositOffer) {
        this.depositOffer = depositOffer;
    }

    public Long getRemittanceOffer() {
        return remittanceOffer;
    }

    public void setRemittanceOffer(Long remittanceOffer) {
        this.remittanceOffer = remittanceOffer;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public Long[] getBillIds() {
        return billIds;
    }

    public void setBillIds(Long[] billIds) {
        this.billIds = billIds;
    }

    public Long getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
