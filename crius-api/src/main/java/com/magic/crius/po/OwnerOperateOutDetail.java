package com.magic.crius.po;

/**
 * 人工出款详情
 */
public class OwnerOperateOutDetail {

    private Integer id;

    private Long ownerId;

    private Long operateOutMoneyCount;  //出款金额

    private Integer operateOutNum;  //出款次数

    private Integer operateOutType; //出款类型  --条件

    private String operateOutTypeName;  //出款类型

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

    public Long getOperateOutMoneyCount() {
        return operateOutMoneyCount;
    }

    public void setOperateOutMoneyCount(Long operateOutMoneyCount) {
        this.operateOutMoneyCount = operateOutMoneyCount;
    }

    public Integer getOperateOutNum() {
        return operateOutNum;
    }

    public void setOperateOutNum(Integer operateOutNum) {
        this.operateOutNum = operateOutNum;
    }

    public Integer getOperateOutType() {
        return operateOutType;
    }

    public void setOperateOutType(Integer operateOutType) {
        this.operateOutType = operateOutType;
    }

    public String getOperateOutTypeName() {
        return operateOutTypeName;
    }

    public void setOperateOutTypeName(String operateOutTypeName) {
        this.operateOutTypeName = operateOutTypeName == null ? null : operateOutTypeName.trim();
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}