package com.magic.crius.po;

/**
 * 会员交易记录汇总
 */
public class UserTradeSummary {


    private Long id;

    private Long ownerId;

    private Long userId;

    private Integer flowType;   //出入款类型 ActionType

    private Integer summaryType;    //汇总类型  SummaryType

    private Long totalMoney;   //汇总金额

    private Integer totalCount; //汇总数量

    private Long createTime;    //创建时间

    private Long updateTime;    //修改时间

    private Long lastMoney; //最后变动金额

    private Long maxMoney;  //最大变动金额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Integer getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(Integer summaryType) {
        this.summaryType = summaryType;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

    public Long getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(Long lastMoney) {
        this.lastMoney = lastMoney;
    }

    public Long getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Long maxMoney) {
        this.maxMoney = maxMoney;
    }
}