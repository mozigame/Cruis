package com.magic.crius.po;

/**
 * 会员账号汇总
 */
public class UserAccountSummary {

    private Integer id;

    private Long ownerId;

    private Long userId;

    private Long flowNum;   //充值次数

    private Long flowCount; //充值金额

    private Long outNum;    //提现次数

    private Long outCount;  //提现金额

    private Integer pdate;

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

    public Long getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Long flowNum) {
        this.flowNum = flowNum;
    }

    public Long getFlowCount() {
        return flowCount;
    }

    public void setFlowCount(Long flowCount) {
        this.flowCount = flowCount;
    }

    public Long getOutNum() {
        return outNum;
    }

    public void setOutNum(Long outNum) {
        this.outNum = outNum;
    }

    public Long getOutCount() {
        return outCount;
    }

    public void setOutCount(Long outCount) {
        this.outCount = outCount;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}