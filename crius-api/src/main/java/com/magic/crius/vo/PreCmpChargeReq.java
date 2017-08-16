package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:46
 * 公司入款（成功）
 */
public class PreCmpChargeReq {

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
    @JSONField(name = "Currency")
    private String currency;
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "BankCode")
    private String bankCode;    //用户银行代号
    @JSONField(name = "BankNum")
    private String bankNum;   //用户银行卡号
    @JSONField(name = "BankHolder")
    private String bankHolder;  //用户银行卡持有者名字
    @JSONField(name = "InBankCode")
    private String inBankCode;//转入公司账号的银行代号
    @JSONField(name = "InBankNum")
    private String inBankNum;//转入公司账号的银行卡号
    @JSONField(name = "InBankHolder")
    private String inBankHolder;//转入公司账号的银行卡持有者
    @JSONField(name = "InBankBranch")
    private String inBankBranch;//转入公司账号的银行卡分支
    @JSONField(name = "Remark")
    private String remark;//备注
    @JSONField(name = "InTime")
    private Long inTime;//转入时间
    @JSONField(name = "ProduceTime")
    private Long produceTime;//注入kafka的ms时间

    @JSONField(name = "HandlerId")
    private Long handlerId; //审核人id
    @JSONField(name = "HandlerName")
    private String handlerName; //审核人名称
    @JSONField(name = "NeedBettAmount")
    private Long needBettAmount;    //待打码量
    @JSONField(name = "ChargeType")
    private Integer chargeType; //入款类型

    @JSONField(name = "ChargeAmount")
    private Long chargeAmount;    //公司充值金额

    @JSONField(name = "Balance")
    private Long balance;   //余额


    @JSONField(name = "InBankName")
    private String inBankName;  //银行名称

    /**
     * 消费时间
     */
    private Long consumerTime;


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

    public Long getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getInBankNum() {
        return inBankNum;
    }

    public void setInBankNum(String inBankNum) {
        this.inBankNum = inBankNum;
    }

    public String getBankHolder() {
        return bankHolder;
    }

    public void setBankHolder(String bankHolder) {
        this.bankHolder = bankHolder;
    }

    public String getInBankCode() {
        return inBankCode;
    }

    public void setInBankCode(String inBankCode) {
        this.inBankCode = inBankCode;
    }


    public String getInBankHolder() {
        return inBankHolder;
    }

    public void setInBankHolder(String inBankHolder) {
        this.inBankHolder = inBankHolder;
    }

    public String getInBankBranch() {
        return inBankBranch;
    }

    public void setInBankBranch(String inBankBranch) {
        this.inBankBranch = inBankBranch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getInTime() {
        return inTime;
    }

    public void setInTime(Long inTime) {
        this.inTime = inTime;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public Long getNeedBettAmount() {
        return needBettAmount;
    }

    public void setNeedBettAmount(Long needBettAmount) {
        this.needBettAmount = needBettAmount;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getInBankName() {
        return inBankName;
    }

    public void setInBankName(String inBankName) {
        this.inBankName = inBankName;
    }

    public Long getConsumerTime() {
        return consumerTime;
    }

    public void setConsumerTime(Long consumerTime) {
        this.consumerTime = consumerTime;
    }
}
