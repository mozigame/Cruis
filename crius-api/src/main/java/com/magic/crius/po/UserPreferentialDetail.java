package com.magic.crius.po;

/**
 * 会员优惠明细
 */
public class UserPreferentialDetail {

    private Integer id;

    private Long ownerId;

    private Long userId;    //--条件

    private Long preferentialMoneyCount;    //优惠金额

    private Integer preferentialNum;    //优惠次数

    private Integer preferentialType;   //优惠类型  --条件

    private String preferentialTypeName;    //优惠类型名称

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}