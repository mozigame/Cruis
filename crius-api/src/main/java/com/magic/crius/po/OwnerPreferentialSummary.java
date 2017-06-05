package com.magic.crius.po;

/**
 * 优惠汇总
 */
public class OwnerPreferentialSummary {

    private Integer id;

    private Long ownerId;

    private Long preferentialMoneyCount;    //优惠金额

    private Integer preferentialNum;    //优惠次数

    private Integer preferentialType;   //优惠类型

    private String preferentialTypeName;    //优惠类型名称

    private Integer pdate;  //统计日期

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

    public Long getPreferentialMoneyCount() {
        return preferentialMoneyCount;
    }

    public void setPreferentialMoneyCount(Long preferentialMoneyCount) {
        this.preferentialMoneyCount = preferentialMoneyCount;
    }

    public Integer getPreferentialNum() {
        return preferentialNum;
    }

    public void setPreferentialNum(Integer preferentialNum) {
        this.preferentialNum = preferentialNum;
    }

    public Integer getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(Integer preferentialType) {
        this.preferentialType = preferentialType;
    }

    public String getPreferentialTypeName() {
        return preferentialTypeName;
    }

    public void setPreferentialTypeName(String preferentialTypeName) {
        this.preferentialTypeName = preferentialTypeName == null ? null : preferentialTypeName.trim();
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}