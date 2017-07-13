package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 15:38
 */
public class AmountVo {

    @JSONField(name = "AmountTypeId")
    private Long amountTypeId;
    @JSONField(name = "AmountTypeName")
    private String amountTypeName;
    @JSONField(name = "Amount")
    private Long amount;


    public Long getAmountTypeId() {
        return amountTypeId;
    }

    public void setAmountTypeId(Long amountTypeId) {
        this.amountTypeId = amountTypeId;
    }

    public String getAmountTypeName() {
        return amountTypeName;
    }

    public void setAmountTypeName(String amountTypeName) {
        this.amountTypeName = amountTypeName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

	@Override
	public String toString() {
		return "AmountVo [amountTypeId=" + amountTypeId + ", amountTypeName=" + amountTypeName + ", amount=" + amount
				+ "]";
	}
    
    
}
