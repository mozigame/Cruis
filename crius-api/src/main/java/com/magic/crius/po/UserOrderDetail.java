package com.magic.crius.po;

/**
 * 注单详情
 */
public class UserOrderDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private String gameId;

    private Long orderId;

    private Long orderCount;    //投注金额

    private Long effectOrderCount;  //有效投注额度

    private Long payOffCount;   //派彩金额

    private Integer orderState; //交易状态

    private Integer pdate;  //统计日期

    private String orderExtent; //扩展数据

    private Long createTime;

    private Long updateTime;

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getEffectOrderCount() {
        return effectOrderCount;
    }

    public void setEffectOrderCount(Long effectOrderCount) {
        this.effectOrderCount = effectOrderCount;
    }

    public Long getPayOffCount() {
        return payOffCount;
    }

    public void setPayOffCount(Long payOffCount) {
        this.payOffCount = payOffCount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
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

    public String getOrderExtent() {
        return orderExtent;
    }

    public void setOrderExtent(String orderExtent) {
        this.orderExtent = orderExtent;
    }
}