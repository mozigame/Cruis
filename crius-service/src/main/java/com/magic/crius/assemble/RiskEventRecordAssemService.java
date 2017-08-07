package com.magic.crius.assemble;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.analysis.utils.DateKit;
import com.magic.analysis.utils.JsonUtils;
import com.magic.crius.po.RiskEventDetail;
import com.magic.crius.po.RiskEventRecord;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.service.RiskEventDetailService;
import com.magic.crius.service.RiskEventRecordService;
import com.magic.crius.vo.RiskRecordReq;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:20
 */
@Service
public class RiskEventRecordAssemService {

    private static final Logger logger = Logger.getLogger(RiskEventRecordAssemService.class);
    @Resource
    private BaseOrderReqService baseOrderReqService;
    @Resource
    private RiskEventRecordService riskEventRecordService;
    @Resource
    private RiskEventDetailService riskEventDetailService;

   
	
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
	
	private Integer getObjectInteger(Object obj){
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
	
	private String getShort(String str, int size){
		if(str!=null){
			if(str.length()>size){
				return str.substring(0, size);
			}
		}
		return str;
	}
    public void procKafkaData(RiskRecordReq req) {
    	RiskEventRecord riskEventRecord=new RiskEventRecord();
    	riskEventRecord.setCreateTime(new Date().getTime());
    	riskEventRecord.setEventTimeNs(req.getEventTimeNs());
//    	riskEventRecord.setPdate(req.getRiskTypeId());
    	riskEventRecord.setRiskEventId(req.getRiskEventId());
    	riskEventRecord.setRiskType(req.getRiskTypeId());
    	riskEventRecord.setTitle(req.getRiskEventTitle());
    	riskEventRecord.setRiskInfoMsg(getShort(req.getRiskInfoMsg(), 990));
    	riskEventRecord.setOwnerId(req.getOwnerId());
    	if(req.getEventTimeNs()!=null){
    		try {
				Date date = new Date(req.getEventTimeNs()/1000000);
				riskEventRecord.setPdate(Integer.valueOf(DateKit.format(date, "yyyyMMdd")));
			} catch (NumberFormatException e) {
				logger.warn("----eventTimeNs:"+req.getEventTimeNs()+" invalid pdate=null");
			}
    	}
    	this.riskEventRecordService.insert(riskEventRecord);
    	logger.info("----riskEventRecord--riskType="+req.getRiskTypeId()+" id="+riskEventRecord.getId()+" eventTimeNs="+riskEventRecord.getEventTimeNs());

    	List<RiskEventDetail> detailList=new ArrayList<>();
    	List<Map<String, Object>> mapList=req.getEventInfos();
    	RiskEventDetail detail=null;
    	for(Map<String, Object> map:mapList){
    		detail=new RiskEventDetail();
    		detail.setRiskEventRecordId(riskEventRecord.getId());
    		detail.setUserId(getObjectLong(map.get("UserId")));map.remove("UserId");
    		detail.setIp(getObjectLong(map.get("Ip")));map.remove("Ip");
    		if(map.containsKey("Time")){
    			detail.setTime(getObjectLong(map.get("Time")));map.remove("Time");
    		}
    		else if(map.containsKey("ProduceTime")){
    			detail.setTime(getObjectLong(map.get("ProduceTime")));map.remove("ProduceTime");
    		}
    		detail.setUserName((String)map.get("UserName"));map.remove("UserName");
    		detail.setJsonData(JsonUtils.toJsonStringTrimNull(map));
    		detailList.add(detail);
    	}
    	this.riskEventDetailService.batchInsert(detailList);
    	
//        if (req.getReqId() != null && baseOrderReqService.getByReqId(req.getReqId()) == null) {
//            if (!baseOrderReqService.save(req)) {
//                //todo
//            }
//        } else {
//            logger.warn("data not matching,"+ JSON.toJSONString(req));
//        }
    }

}