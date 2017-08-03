package com.magic.crius.assemble;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.ProxyInfo;
import com.magic.crius.service.ProxyInfoService;
import com.magic.crius.service.dubbo.CriusOutDubboService;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.user.entity.User;

import redis.clients.jedis.Jedis;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 15:16
 */
@Service
public class ProxyInfoAssemService {

    private static Logger logger = Logger.getLogger(ProxyInfoAssemService.class);
    @Resource
    private CriusOutDubboService criusOutDubboService;
    @Resource
    private ProxyInfoService proxyInfoService;

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;
    
    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    public void init(Date date) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String hhStr = DateUtil.formatDateTime(date, "yyyyMMddHH");
                    Date endTime = DateUtil.parseDate(hhStr, "yyyyMMddHH");
                    Calendar startTime = Calendar.getInstance();
                    startTime.setTime(DateUtil.parseDate(hhStr, "yyyyMMddHH"));
                    startTime.add(Calendar.HOUR, -1);
                    batchSave(startTime.getTimeInMillis(), endTime.getTime());
                } catch (Exception e) {
//                    e.printStackTrace();
                	logger.error("----proxyInfo-init---", e);
                }
            }
        });

    }
    
    

    public void batchProxyInfoSync(){
    	executorService.execute(new Runnable() {
            @Override
            public void run() {
		    	Long page=null;
		        try {
		        	Jedis jedis = criusJedisFactory.getInstance();
		        	page=jedis.incr(RedisConstants.REDIS_PROXY_INFO_SYNC_PAGE);
		        	jedis.expire(RedisConstants.REDIS_PROXY_INFO_SYNC_PAGE,3*60*60);//3个小时存活时间
		        	int batchSize=200;
		        	batchProxyInfoSyncTask(page.intValue(), batchSize);
				} catch (Exception e) {
					logger.error("-----batchProxyInfoSync---page="+page+" size="+RedisConstants.REDIS_PROXY_INFO_SYNC_PAGE, e);
				}
            }
        });

    	
    }
    
    private void batchProxyInfoSyncTask(Integer page, Integer size){
    	Integer offset=(page-1)*size;
    	logger.info("-----batchProxyInfoSyncTask--start--page="+page+" offset="+offset+" size="+size);
    	List<User> list= criusOutDubboService.getAgentListByPage(offset, size);
    	
    	if(list.size()>0){
    		List<Long> proxyIdList=new ArrayList<>(); 
    		for(User user:list){
    			proxyIdList.add(user.getUserId());
    		}
    		List<ProxyInfo> proxyList=this.proxyInfoService.getProxyInfoList(proxyIdList);
    		List<ProxyInfo> changedList=new ArrayList<>();
    		String key=null;
    		for(User user:list){
    			key=user.getOwnerId()+"_"+user.getOwnerName();
    			for(ProxyInfo proxyInfo:proxyList){
    				if(user.getUserId().longValue()==proxyInfo.getProxyId().longValue()){
    					if(!key.equals(proxyInfo.getShareholderId()+"_"+proxyInfo.getShareholderName())){
    						proxyInfo.setShareholderId(user.getOwnerId());
    						proxyInfo.setShareholderName(user.getOwnerName());
    						proxyInfo.setOwnerId(user.getOwnerId());
    		                proxyInfo.setOwnerName(user.getOwnerName());
    						changedList.add(proxyInfo);
    					}
    					break;
    				}
    			}
    		}
    		logger.info("-----batchProxyInfoSyncTask--update--changedList="+changedList.size());
    		if(!CollectionUtils.isEmpty(changedList)){
    			for(ProxyInfo proxyInfo:changedList){
    				proxyInfoService.update(proxyInfo);
    			}
    		}
    	}
    	else{
    		Jedis jedis = criusJedisFactory.getInstance();
    		jedis.del(RedisConstants.REDIS_PROXY_INFO_SYNC_PAGE);
    		logger.info("batchProxyInfoSyncTask reset :" + RedisConstants.REDIS_PROXY_INFO_SYNC_PAGE+" page:"+page);
    	}
    }

    public void batchSave(Long startTime, Long endTime) {
        List<User> list = criusOutDubboService.getDateAgents(startTime, endTime); //获取账号系统中某个时间内的代理
        if (list != null && list.size() > 0) {
            logger.info("get proxyInfoList ,size : " + list.size() + ", thread : " + Thread.currentThread().getName());

            List<Long> findProxyIds = new ArrayList<>();
            for (User user : list) {

                findProxyIds.add(user.getUserId());
            }

            List<Long> existProxyIds = proxyInfoService.getExistIds(findProxyIds);
            List<ProxyInfo> existProxyInfos = new ArrayList<>();
            List<ProxyInfo> noExistProxyInfos = new ArrayList<>();

            for (User user : list) {
                ProxyInfo proxyInfo = new ProxyInfo();
                proxyInfo.setProxyId(user.getUserId());
                proxyInfo.setProxyName(user.getUsername());
                proxyInfo.setShareholderId(user.getOwnerId());
                proxyInfo.setShareholderName(user.getOwnerName());
                proxyInfo.setOwnerId(user.getOwnerId());
                proxyInfo.setOwnerName(user.getOwnerName());
                if (existProxyIds.contains(user.getUserId())) {
                    existProxyInfos.add(proxyInfo);
                } else {
                    noExistProxyInfos.add(proxyInfo);
                }
            }
            
            if(!CollectionUtils.isEmpty(noExistProxyInfos)){
	            //todo 插入不存在的代理信息
	            if (!proxyInfoService.batchInsert(noExistProxyInfos)) {
	            	logger.warn("batchSave startTime="+startTime+" endTime="+endTime+" noExistProxyInfos="+noExistProxyInfos.size());
	            }
            }
            for (ProxyInfo info : existProxyInfos) {
                proxyInfoService.update(info);
            }
        }
    }


}
