package com.magic.crius.po;

/**
 * 会员入款明细
 */
public class UserFlowMoneyDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private String merchantCode;   //商户号

    private String merchantName;    //商家名称

    private Long orderCount;    //入款金额

    private Integer state;  //状态

    private Integer payMethod;  //支付方式

    private Integer flowType;   //入款方式

    private Long flowId;    //入款编号

    private Long orderId;   //订单编号

    private Integer pdate;  //统计日期

    private Long handlerId; //审核人编号

    private String handlerName; //审核人名称

    private Long createTime;

    private Long updateTime;

    private String remark;//备注

    /**
     * paySystemCode
     */
    private Integer paySystemCode;

    /**
     * paySystemName
     */
    private String paySystemName;


    private Integer flowDetailType; //入款详细类型

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
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
        this.handlerName = handlerName == null ? null : handlerName.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlowDetailType() {
        return flowDetailType;
    }

    public void setFlowDetailType(Integer flowDetailType) {
        this.flowDetailType = flowDetailType;
    }

    /**
     * getter for paySystemCode
     */

    public Integer getPaySystemCode() {
        return paySystemCode;
    }

    /**
     * setter for paySystemCode
     */
    public void setPaySystemCode(Integer paySystemCode) {
        this.paySystemCode = paySystemCode;
    }

    /**
     * getter for paySystemName
     */

    public String getPaySystemName() {
        return paySystemName;
    }

    /**
     * setter for paySystemName
     */
    public void setPaySystemName(String paySystemName) {
        this.paySystemName = paySystemName;
    }
}