package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 15:35
 */
public class AgentHallBillVo {

    @JSONField(name = "PlatformId")
    private Long platformId;
    @JSONField(name = "HallTypeId") 
    private Long hallTypeId;
    @JSONField(name = "VaildBettAmount")
    private Long vaildBettAmount;   // 有效投注额
    @JSONField(name = "PayoffAmount")
    private Long payoffAmount;   // 派彩
    @JSONField(name = "CashbackAmount")
    private Long cashbackAmount;   // 返水
    @JSONField(name = "CostAmount")
    private long costAmount;   // 行政费用
    @JSONField(name = "RebateAmount")
    private Long rebateAmount;   // 可获退佣费用

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getHallTypeId() {
        return hallTypeId;
    }

    public void setHallTypeId(Long hallTypeId) {
        this.hallTypeId = hallTypeId;
    }

    public Long getVaildBettAmount() {
        return vaildBettAmount;
    }

    public void setVaildBettAmount(Long vaildBettAmount) {
        this.vaildBettAmount = vaildBettAmount;
    }

    public Long getPayoffAmount() {
        return payoffAmount;
    }

    public void setPayoffAmount(Long payoffAmount) {
        this.payoffAmount = payoffAmount;
    }

    public Long getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(Long cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(long costAmount) {
        this.costAmount = costAmount;
    }

    public Long getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(Long rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

	@Override
	public String toString() {
		return "AgentHallBillVo [platformId=" + platformId + ", hallTypeId=" + hallTypeId + ", vaildBettAmount="
				+ vaildBettAmount + ", payoffAmount=" + payoffAmount + ", cashbackAmount=" + cashbackAmount
				+ ", costAmount=" + costAmount + ", rebateAmount=" + rebateAmount + "]";
	}
    
    
}
