package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:01
 * 人工提现（人工提出）（成功）
 */
public class OperateWithDrawReq {

    @JSONField(name = "BillIds")
    private Long billIds[];
    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "UserIds")
    private Long userIds[];
    @JSONField(name = "Amount")
    private Long amount;
    @JSONField(name = "Currency")
    private String currency;
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "HandlerId")
    private Long handlerId;
    @JSONField(name = "HandlerName")
    private String handlerName;
    @JSONField(name = "WithdrawType")
    private Integer withdrawType;   //提现类型
    @JSONField(name = "Remark")
    private String remark;  //备注
    @JSONField(name = "ProduceTime")
    private Long produceTime;

    @JSONField(name = "Balance")
    private Long balance;   //余额

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long[] getBillIds() {
        return billIds;
    }

    public void setBillIds(Long[] billIds) {
        this.billIds = billIds;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
