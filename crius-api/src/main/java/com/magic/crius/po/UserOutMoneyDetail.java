package com.magic.crius.po;

/**
 * 会员出款明细
 */
public class UserOutMoneyDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private Long orderCount;    //出款金额

    private Long taxCount;  //费用扣除

    private Integer state;  //状态

    private Long orderId;   //出款编号

    private Integer pdate;  //统计日期

    private Long handlerId; //审核人编号

    private String handlerName; //审核人名称

    private Long createTime;

    private Long updateTime;

    private Long costAmount;//行政成本

    private Long offerAmount;//优惠金额

    private Long feeAmount;//手续费



    private Integer outDetailType;//出款详细类型

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public Long getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(Long offerAmount) {
        this.offerAmount = offerAmount;
    }

    public Long getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Long feeAmount) {
        this.feeAmount = feeAmount;
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

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getTaxCount() {
        return taxCount;
    }

    public void setTaxCount(Long taxCount) {
        this.taxCount = taxCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Integer getOutDetailType() {
        return outDetailType;
    }

    public void setOutDetailType(Integer outDetailType) {
        this.outDetailType = outDetailType;
    }
}