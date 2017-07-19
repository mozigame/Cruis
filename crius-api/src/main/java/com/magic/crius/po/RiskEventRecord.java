package com.magic.crius.po;

import java.util.Date;

/**
 * 风控事件记录
 * @author Justin
 * @date 20170718
 *
 */
public class RiskEventRecord {
	private Long id;
	private Long eventTimeNs;
	private Integer riskType;
	private Integer riskEventId;
	private Integer pdate;
	private Long createTime;
	private String title;
	private String riskInfoMsg;
	private Long ownerId;
	
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
	public Long getEventTimeNs() {
		return eventTimeNs;
	}
	public void setEventTimeNs(Long eventTimeNs) {
		this.eventTimeNs = eventTimeNs;
	}
	
	public Integer getPdate() {
		return pdate;
	}
	public void setPdate(Integer pdate) {
		this.pdate = pdate;
	}
	public Integer getRiskType() {
		return riskType;
	}
	public void setRiskType(Integer riskType) {
		this.riskType = riskType;
	}
	public Integer getRiskEventId() {
		return riskEventId;
	}
	public void setRiskEventId(Integer riskEventId) {
		this.riskEventId = riskEventId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRiskInfoMsg() {
		return riskInfoMsg;
	}
	public void setRiskInfoMsg(String riskInfoMsg) {
		this.riskInfoMsg = riskInfoMsg;
	}
	
	
}
