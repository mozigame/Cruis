package com.magic.crius.po;

/**
 * 风控事件明细
 * @author Justin
 * @date 20170718
 *
 */
public class RiskEventDetail {
	private Long id;
	private Long riskEventRecordId;
	private Long userId;
	private String userName;
	private Long ip;
	private Long time;
	private String jsonData;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getRiskEventRecordId() {
		return riskEventRecordId;
	}
	public void setRiskEventRecordId(Long riskEventRecordId) {
		this.riskEventRecordId = riskEventRecordId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getIp() {
		return ip;
	}
	public void setIp(Long ip) {
		this.ip = ip;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	
}