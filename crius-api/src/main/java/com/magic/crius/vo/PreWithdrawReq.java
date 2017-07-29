package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:55
 * 用户提现(会员出款)（成功）
 */
public class PreWithdrawReq {

    @JSONField(name = "BillId")
    private Long billId;
    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "UserId")
    private Long userId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;

    @JSONField(name = "UserLevel")
    private Long userLevel;
    @JSONField(name = "Remark")
    private String remark;
    @JSONField(name = "ProduceTime")
    private Long produceTime;//注入kafka的ms时间
    @JSONField(name = "HandlerId")
    private Long handlerId; //审核人id
    @JSONField(name = "HandlerName")
    private String handlerName; //审核人账号
    @JSONField(name = "ReqWithdrawAmount")
    private Long reqWithdrawAmount;    //申请提现额度
    @JSONField(name = "RealWithdrawAmount")
    private Long realWithdrawAmount;    //实际提现额度
    @JSONField(name = "NeedPayAmount")
    private Long needPayAmount; //费用扣除

    @JSONField(name = "Balance")
    private Long balance;   //余额


    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Long userLevel) {
        this.userLevel = userLevel;
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

    public Long getReqWithdrawAmount() {
        return reqWithdrawAmount;
    }

    public void setReqWithdrawAmount(Long reqWithdrawAmount) {
        this.reqWithdrawAmount = reqWithdrawAmount;
    }

    public Long getRealWithdrawAmount() {
        return realWithdrawAmount;
    }

    public void setRealWithdrawAmount(Long realWithdrawAmount) {
        this.realWithdrawAmount = realWithdrawAmount;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getNeedPayAmount() {
        return needPayAmount;
    }

    public void setNeedPayAmount(Long needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
