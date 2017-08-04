package com.magic.crius.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class RiskRecordReq {
	public RiskRecordReq(){
		
	}
	
	private Integer getObjectInt(Object obj){
		if(obj==null){
			return null;
		}
		else if(obj instanceof Integer){
			return (Integer) obj;
		}
		else if(obj instanceof Long){
			return ((Long)obj).intValue();
		}
		else{
			return Integer.parseInt((String)obj);
		}
	}
	
	private Long getObjectLong(Object obj){
		if(obj==null){
			return null;
		}
		else if(obj instanceof Integer){
			return 0l+(Integer) obj;
		}
		else if(obj instanceof Long){
			return ((Long)obj);
		}
		else{
			return Long.parseLong((String)obj);
		}
	}
	
	public RiskRecordReq(JSONObject object){
//		object.getString(RECORD);
//		System.out.println(object);
		this.riskTypeId=getObjectInt(object.get("RiskTypeId"));
		this.riskEventId=getObjectInt(object.get("RiskEventId"));
		this.riskEventTitle=(String)object.get("RiskEventTitle");
		this.riskInfoMsg=(String)object.get("RiskInfoMsg");
		this.riskTime=getObjectLong(object.get("RiskTime"));
		this.ownerId=getObjectLong(object.get("OwnerId"));
		
		JSONObject riskData=(JSONObject)object.get("RiskData");
		JSONObject eventRecordInfo =(JSONObject)riskData.get("EventRecordInfo");
		this.eventTimeNs=(Long)eventRecordInfo.get("EventTimeNs");
		
		List<Map<String, Object>> mapList=new ArrayList<>();
		JSONArray arrays=riskData.getJSONArray("EventInfos");
		Map<String, Object> map=null;
		for(int i=0; i<arrays.size(); i++){
			map=new HashMap<>();
			JSONObject jsonObj=(JSONObject)arrays.get(i);
			Set<String> keys= jsonObj.keySet();
			for(String key:keys){
				map.put(key, jsonObj.get(key));
			}
			mapList.add(map);
		}
		this.eventInfos=mapList;
	}

	@JSONField(name = "RiskTypeId")
	private Integer riskTypeId;
	@JSONField(name = "RiskEventId")
	private Integer riskEventId;
	@JSONField(name = "RiskTime")
	private Long riskTime;
	@JSONField(name = "RiskEventTitle")
	private String riskEventTitle;
	@JSONField(name = "RiskInfoMsg")
	private String riskInfoMsg;
	@JSONField(name = "OwnerId")
	private Long ownerId;
	@JSONField(name = "EventTimeNs")
	private Long eventTimeNs;
	@JSONField(name = "RiskData")
	private Map<String, Object> riskData; 
	private List<Map<String, Object>> eventInfos; 
	
	
	
	
	public Map<String, Object> getRiskData() {
		return riskData;
	}

	public void setRiskData(Map<String, Object> riskData) {
		this.riskData = riskData;
	}

	public List<Map<String, Object>> getEventInfos() {
		return eventInfos;
	}
	public void setEventInfos(List<Map<String, Object>> eventInfos) {
		this.eventInfos = eventInfos;
	}
	public Long getEventTimeNs() {
		return eventTimeNs;
	}
	public void setEventTimeNs(Long eventTimeNs) {
		this.eventTimeNs = eventTimeNs;
	}
	public Integer getRiskTypeId() {
		return riskTypeId;
	}
	public void setRiskTypeId(Integer riskTypeId) {
		this.riskTypeId = riskTypeId;
	}
	public Integer getRiskEventId() {
		return riskEventId;
	}
	public void setRiskEventId(Integer riskEventId) {
		this.riskEventId = riskEventId;
	}
	public Long getRiskTime() {
		return riskTime;
	}
	public void setRiskTime(Long riskTime) {
		this.riskTime = riskTime;
	}
	public String getRiskEventTitle() {
		return riskEventTitle;
	}
	public void setRiskEventTitle(String riskEventTitle) {
		this.riskEventTitle = riskEventTitle;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getRiskInfoMsg() {
		return riskInfoMsg;
	}
	public void setRiskInfoMsg(String riskInfoMsg) {
		this.riskInfoMsg = riskInfoMsg;
	}
	
	
}
