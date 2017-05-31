package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:01
 * 人工提现（成功）
 */
public class OperateWithDrawReq {

    private Long ReqId;
    private Long UserIds[];
    private Long Amount;
    private String Currency;
    private Integer Rate;
    private Long HandlerId;
    private String HandlerName;
    //提现类型
    private Integer WithdrawType;
    //备注
    private String Remark;
    private Long ProduceTime;

    public Long getProduceTime() {
        return ProduceTime;
    }

    public void setProduceTime(Long produceTime) {
        ProduceTime = produceTime;
    }

    public Long getReqId() {
        return ReqId;
    }

    public void setReqId(Long reqId) {
        ReqId = reqId;
    }

    public Long[] getUserIds() {
        return UserIds;
    }

    public void setUserIds(Long[] userIds) {
        UserIds = userIds;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public Integer getRate() {
        return Rate;
    }

    public void setRate(Integer rate) {
        Rate = rate;
    }

    public Long getHandlerId() {
        return HandlerId;
    }

    public void setHandlerId(Long handlerId) {
        HandlerId = handlerId;
    }

    public String getHandlerName() {
        return HandlerName;
    }

    public void setHandlerName(String handlerName) {
        HandlerName = handlerName;
    }

    public Integer getWithdrawType() {
        return WithdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        WithdrawType = withdrawType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
