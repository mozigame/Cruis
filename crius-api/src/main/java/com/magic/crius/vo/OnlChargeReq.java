package com.magic.crius.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:42
 * 用户充值成功(线上入款)
 */
public class OnlChargeReq {

    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "OrderId")
    private Long orderId;   //支付订单ID
    @JSONField(name = "BillId")
    private Long billId;    //账单id
    @JSONField(name = "UserId")
    private Long userId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "ChargeAmount")
    private Long chargeAmount;    //充值金额
    @JSONField(name = "Currency")
    private String currency;
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "MerchantCode")
    private String merchantCode;  //充值的商户号
    @JSONField(name = "MerchantName")
    private String merchantName;    //充值商户名称
    @JSONField(name = "PaySystemCode")
    private Integer paySystemCode;  //充入的第三方系统号
    @JSONField(name = "PaySystemName")
    private String paySystemName;   //充入的第三方的系统名字
    @JSONField(name = "ProduceTime")
    private Long produceTime;
    @JSONField(name = "ChargeType")
    private Integer chargeType; //支付类型

    @JSONField(name = "Balance")
    private Long balance;   //余额

    @JSONField(name = "Remark")
    private String remark;//备注

    /**
     * 处理用户id
     */
    @JSONField(name = "HandlerId")
    private String handlerId;

    /**
     * 处理人Name
     */
    @JSONField(name = "HandlerName")
    private String handlerName;


    /**
     * 消费时间
     */
    private Long consumerTime;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPaySystemName() {
        return paySystemName;
    }

    public void setPaySystemName(String paySystemName) {
        this.paySystemName = paySystemName;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public Integer getPaySystemCode() {
        return paySystemCode;
    }

    public void setPaySystemCode(Integer paySystemCode) {
        this.paySystemCode = paySystemCode;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    /**
     * getter for handlerId
     */

    public String getHandlerId() {
        return handlerId;
    }

    /**
     * setter for handlerId
     */
    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    /**
     * getter for handlerName
     */

    public String getHandlerName() {
        return handlerName;
    }

    /**
     * setter for handlerName
     */
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

    public Long getConsumerTime() {
        return consumerTime;
    }

    public void setConsumerTime(Long consumerTime) {
        this.consumerTime = consumerTime;
    }
}
