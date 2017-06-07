package com.magic.crius.po;

/**
 * 人工入款汇总
 */
public class OwnerOperateFlowSummmary {
    private Integer id;

    private Long ownerId;

    private Long operateFlowMoneyCount; //入款金额

    private Integer operateFlowNum; //入款次数

    private Integer operateFlowType;    //入款类型  --条件

    private String operateFlowTypeName; //入款类型名称

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

    public Long getOperateFlowMoneyCount() {
        return operateFlowMoneyCount;
    }

    public void setOperateFlowMoneyCount(Long operateFlowMoneyCount) {
        this.operateFlowMoneyCount = operateFlowMoneyCount;
    }

    public Integer getOperateFlowNum() {
        return operateFlowNum;
    }

    public void setOperateFlowNum(Integer operateFlowNum) {
        this.operateFlowNum = operateFlowNum;
    }

    public Integer getOperateFlowType() {
        return operateFlowType;
    }

    public void setOperateFlowType(Integer operateFlowType) {
        this.operateFlowType = operateFlowType;
    }

    public String getOperateFlowTypeName() {
        return operateFlowTypeName;
    }

    public void setOperateFlowTypeName(String operateFlowTypeName) {
        this.operateFlowTypeName = operateFlowTypeName == null ? null : operateFlowTypeName.trim();
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}