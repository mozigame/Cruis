package com.magic.crius.assemble;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:26
 */
@Service
public class UserLevelAssemService {

    private static final Logger logger = Logger.getLogger(UserLevelAssemService.class);

    @Resource
    private MemberConditionVoService memberConditionVoService;
    @Resource
    private UserInfoService userInfoService;
    
    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> template;

    private static final String TOPIC = "plutus";

    private static final String DATA = "Data";
    private static final String DATA_TYPE = "DataType";


    public void updateLevel(UserLevelReq userLevelReq) {
    	logger.info("----updateLevel--userId="+userLevelReq.getUserId()+" userLevel="+userLevelReq.getLevelId());
        if (!memberConditionVoService.updateLevel(userLevelReq)) {
            logger.warn("update user level failed, param: " + JSON.toJSONString(userLevelReq));
        }
        
        if (!userInfoService.updateLevel(userLevelReq.getUserId(), userLevelReq.getLevelId())) {
        	logger.warn("update user_info level failed, param: " + JSON.toJSONString(userLevelReq));
        	//处理失败，则延时1秒把消息放回KAFKA
			resendMsg(userLevelReq);
        }
    }
    private static Timer timer = new Timer();
    private static final AtomicInteger resendTaskCount=new AtomicInteger();
    /**
     * 处理失败，则延时1秒把消息放回KAFKA
     * @param userLevelReq
     */
	private void resendMsg(UserLevelReq userLevelReq) {
		timer.schedule(new TimerTask() {
			public void run() {
				// 处理失败，则延时1秒把消息放回KAFKA
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(DATA_TYPE, KafkaConf.DataType.UPDATE_USER_LEVEL.type());
				jsonObject.put(DATA, userLevelReq);
				template.send(TOPIC, jsonObject.toJSONString());
				logger.info("----resendMsg count="+resendTaskCount.incrementAndGet()+" "+userLevelReq.getUserId());
				this.cancel();
			}
		}, 1000);// 1秒
	}

    /**
     * 批量修改一段时间内的会员层级
     *
     * @param date
     */
    public void batchUpdateLevel(Date date) {
        String hhStr = DateUtil.formatDateTime(date, "yyyyMMddHH");
        Date endTime = DateUtil.parseDate(hhStr, "yyyyMMddHH");
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(DateUtil.parseDate(hhStr, "yyyyMMddHH"));
        startTime.add(Calendar.HOUR, -1);
        rectifyLevel(startTime.getTimeInMillis(), endTime.getTime());
    }

    /**
     * 纠正用户层级
     *
     * @param startTime
     * @param endTime
     */
    public void rectifyLevel(Long startTime, Long endTime) {
        List<MemberConditionVo> vos = memberConditionVoService.findPeriodLevels(startTime, endTime);
        logger.info(String.format("batch update user level , startTime : %d, endTime : %d", startTime, endTime));
        if (vos.size() > 0) {
            System.out.println("findPeriodLevels ,size :" + vos.size());
            int i = 0;
            for (MemberConditionVo vo : vos) {
                if (userInfoService.updateLevel(vo.getMemberId(), (long)vo.getLevel())) {
                    i++;
                }
            }
            logger.info("batchUpdateLevel size : " + i);
        }
    }


}
