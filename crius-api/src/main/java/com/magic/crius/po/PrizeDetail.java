package com.magic.crius.po;

/**
 * 彩金明细
 */
public class PrizeDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private Long orderId;   //彩金编号

    private String prizeType;   //彩金类型

    private String prizeTypeName;   //彩金类型名称

    private String gameId;  //游戏id

    private Long orderCount;    //彩金金额

    private Integer pdate;  //统计日期

    private Long createTime;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType == null ? null : prizeType.trim();
    }

    public String getPrizeTypeName() {
        return prizeTypeName;
    }

    public void setPrizeTypeName(String prizeTypeName) {
        this.prizeTypeName = prizeTypeName == null ? null : prizeTypeName.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
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
}