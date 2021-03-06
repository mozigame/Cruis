package com.magic.crius.assemble;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.model.Page;
import com.magic.crius.po.UserInfo;
import com.magic.crius.service.thrift.CriusThirdThriftService;
import org.apache.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;

import redis.clients.jedis.Jedis;

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
    @Resource
    private CriusThirdThriftService criusThirdThriftService;
    
    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> template;

    private static final String TOPIC = "plutus";

    private static final String DATA = "Data";
    private static final String DATA_TYPE = "DataType";
    
    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    public void updateLevel(UserLevelReq userLevelReq) {
    	logger.info("----updateLevel--userId="+userLevelReq.getUserId()+" userLevel="+userLevelReq.getLevelId());
        if (!memberConditionVoService.updateLevel(userLevelReq)) {
            logger.warn("update user level failed, param: " + JSON.toJSONString(userLevelReq));
        }
        
        if (!userInfoService.updateLevel(userLevelReq.getUserId(), userLevelReq.getLevelId())) {
        	logger.warn("update user_info level failed, param: " + JSON.toJSONString(userLevelReq));

            Jedis jedis = null;
            try {
                jedis = criusJedisFactory.getInstance();
                Long count=jedis.incr(RedisConstants.REDIS_USER_LEVEL_UPDATE_COUNT+"_"+userLevelReq.getUserId());
                jedis.expire(RedisConstants.REDIS_USER_LEVEL_UPDATE_COUNT+"_"+userLevelReq.getUserId(), 60);//60秒存活时间
                if(count<=5){//用redis控制次数，超过5次不处理
                    //处理失败，则延时1秒把消息放回KAFKA
                    resendMsg(userLevelReq);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                criusJedisFactory.close(jedis);
            }
        }
    }
    
    
    private static Map<Integer, Timer> timerMap=new HashMap<>();
    private static synchronized Timer getTime(Integer count){
    	count=count%10;
    	Timer timer=timerMap.get(count);
    	if(timer==null){
    		timer=new Timer(""+count);
    		timerMap.put(count, timer);
    	}
    	return timer;
    	
    }
    private static final AtomicInteger resendTaskCount=new AtomicInteger();
    /**
     * 处理失败，则延时1秒把消息放回KAFKA
     * @param userLevelReq
     */
	private void resendMsg(UserLevelReq userLevelReq) {
		Integer count=resendTaskCount.get();
		Timer timer=getTime(count);
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


        String result = null;
        Jedis jedis = null;
        try {
            jedis = criusJedisFactory.getInstance();
            result = jedis.get(RedisConstants.USER_LEVEL_LOCK);
            logger.info("-----batchUpdateLevel--" + RedisConstants.USER_LEVEL_LOCK + "=" + result);
            if (result == null) {
                jedis.incr(RedisConstants.USER_LEVEL_LOCK);
                jedis.expire(RedisConstants.USER_LEVEL_LOCK, 10 * 60);//10分钟存活时间

                String hhStr = DateUtil.formatDateTime(date, "yyyyMMddHH");
                Date endTime = DateUtil.parseDate(hhStr, "yyyyMMddHH");
                Calendar startTime = Calendar.getInstance();
                startTime.setTime(DateUtil.parseDate(hhStr, "yyyyMMddHH"));
                startTime.add(Calendar.HOUR, -1);
                rectifyLevel(startTime.getTimeInMillis(), endTime.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            criusJedisFactory.close(jedis);
        }

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

    /**
     * 纠正为0 的用户层级
     */
    public void rectifyLevelInvalid(Page page) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserLevel(0);
        List<UserInfo> userInfos = userInfoService.findUserLevel(userInfo, page);
        if (userInfos != null && userInfos.size() > 0) {
            int count =0;
            List<Long> invalidUserIds = Lists.newArrayList();
            for (UserInfo info : userInfos) {
                long level = criusThirdThriftService.getMemberLevel(info.getUserId());
                if (level > 0) {
                    if (userInfoService.updateLevel(info.getUserId(), level)) {
                        count++;
                    }
                } else {
                    invalidUserIds.add(info.getUserId());
                }
            }
            ApiLogger.info(String.format("rectifyLevelInvalid finished, count : %d, invalidUserIds : %s", count, JSON.toJSONString(invalidUserIds)));
        }
    }

}
