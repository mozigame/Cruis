package com.magic.crius.po;

/**
 * 返水详情
 */
public class OwnerReforwardDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private String gameType;    //游戏类型

    private Integer orderNum;   //订单数量

    private Long orderCount;    //投注金额

    private Long effectOrderCount;  //有效投注额度

    private Long reforwardMoneyCount;   //返水金额

    private Long orderId;   //

    private Integer pdate;

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

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public Long getReforwardMoneyCount() {
        return reforwardMoneyCount;
    }

    public void setReforwardMoneyCount(Long reforwardMoneyCount) {
        this.reforwardMoneyCount = reforwardMoneyCount;
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
}