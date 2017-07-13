package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 15:37
 *
 */
public class OwnerHallBillVo {

    @JSONField(name = "PlatformId")
    private Long platformId;
    @JSONField(name = "HallTypeId")
    private Long hallTypeId;
    @JSONField(name = "PayOffAmount")
    private Long payOffAmount;  //派彩量
    @JSONField(name = "NeedPayAmount")
    private Long needPayAmount;  //应付款

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

    public Long getPayOffAmount() {
        return payOffAmount;
    }

    public void setPayOffAmount(Long payOffAmount) {
        this.payOffAmount = payOffAmount;
    }

    public Long getNeedPayAmount() {
        return needPayAmount;
    }

    public void setNeedPayAmount(Long needPayAmount) {
        this.needPayAmount = needPayAmount;
    }

	@Override
	public String toString() {
		return "OwnerHallBillVo [platformId=" + platformId + ", hallTypeId=" + hallTypeId + ", payOffAmount="
				+ payOffAmount + ", needPayAmount=" + needPayAmount + "]";
	}
    
    
}
