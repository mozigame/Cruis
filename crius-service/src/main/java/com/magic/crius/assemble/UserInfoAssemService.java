package com.magic.crius.assemble;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.analysis.utils.CommUtils;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserInfo;
import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.service.UserInfoService;
import com.magic.user.vo.MemberConditionVo;

import redis.clients.jedis.Jedis;

/**
 * 用户信息同步服务
 * 算法说明：每个任务执行时向redis的_userInfo_syc_count incr，并返回syc_count
 * 则当前任务处理从syc_count-1到syc_count对应的批次数据。
 * 每批次数据2000，则实际执行的数据为(syc_count-1)*2000到syc_count*2000间的数据量
 * 
 * 更新内容：userAccount,userLevel
 * 
 * User: justin
 * Date: 2017/7/15
 * Time: 14:30
 */
@Service
public class UserInfoAssemService {

    private static final Logger logger = Logger.getLogger(UserInfoAssemService.class);

    @Resource
    private MemberConditionVoService memberConditionVoService;
    @Resource
    private UserInfoService userInfoService;
    
    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;


    /**
     * 批量修改一段时间内的会员层级
     *
     * @param date
     */
    public void batchUserInfoSync() {
    	Jedis jedis = criusJedisFactory.getInstance();
        Long page=jedis.incr(RedisConstants.REDIS_USER_INFO_SYNC_PAGE);
        jedis.expire(RedisConstants.REDIS_USER_INFO_SYNC_PAGE,3*60*60);//3个小时存活时间
        batchUserInfoSyncTask(page.intValue(), RedisConstants.USER_INFO_ASYNC_PAGE_BATCH);
    }

    /**
     * 纠正用户层级
     *
     * @param startTime
     * @param endTime
     */
    public void batchUserInfoSyncTask(int page, int size) {
        List<MemberConditionVo> vos = memberConditionVoService.findByPage(page, size);
        logger.info(String.format("batch update user level , page : %d, size : %d", page, size));
        if (vos.size() > 0) {
            logger.debug("batchUserInfoSyncTask ,size :" + vos.size());
            int batchSize=500;
            List<MemberConditionVo> batchList=new ArrayList<>();
            List<String> nameEmpty_idList=new ArrayList<>(); 
            for (MemberConditionVo vo : vos) {
            	if(vo.getMemberName()==null || vo.getMemberId()==null){//用户账号为空的数据不处理
            		nameEmpty_idList.add(vo.getId());
            		continue;
            	}
            	batchList.add(vo);
            	if(batchList.size()>batchSize){
            		this.userInfoSync(batchList);
            		batchList.clear();
            	}
            }
            if(!CollectionUtils.isEmpty(nameEmpty_idList)){
            	logger.warn("-----memberName/memberId is null idList="+nameEmpty_idList);
            }
            this.userInfoSync(batchList);
            logger.info("batchUserInfoSyncTask end");
        }
        else{//如果数据为空，则检查page是否超出最大值，如果超出则重置page
        	Long rows=memberConditionVoService.getTotalCount();
        	if(rows!=null && rows<page*size){
        		Jedis jedis = criusJedisFactory.getInstance();
        		jedis.del(RedisConstants.REDIS_USER_INFO_SYNC_PAGE);
        	}
        }
    }
    
    
    public void userInfoSync(List<MemberConditionVo> batchList) {
    	if(CollectionUtils.isEmpty(batchList)){
    		return;
    	}
    	long time=System.currentTimeMillis();
    	logger.info("-----userInfoSync--start--batchList="+batchList.size());
    	List<Long> memberIdList=new ArrayList<>();
    	List<Long> agentIdList=new ArrayList<>();
    	
    	for(MemberConditionVo member:batchList){
    		memberIdList.add(member.getMemberId());
    		if(!agentIdList.contains(member.getAgentId())){
    			agentIdList.add(member.getAgentId());
    		}
    	}
    	
    	//根据账号查用户
    	List<UserInfo> userList=this.userInfoService.findUserInfoList(memberIdList, agentIdList);
    	logger.info("-----userInfoSync--userList="+userList.size()+" time="+(System.currentTimeMillis()-time)+" memberIdList="+memberIdList);
    	boolean isExist=false;
    	Long userLevelMember=null;
    	Integer userLevelUser=null;
    	List<MemberConditionVo> newList=new ArrayList<>();
    	List<UserInfo> changeList=new ArrayList<>(); 
    	for(MemberConditionVo member:batchList){
    		isExist=false;
    		for(UserInfo userInfo:userList){
    			if(member.getMemberId().longValue()==userInfo.getUserId().longValue()){
    				isExist=true;
    				userLevelMember=(Long)CommUtils.nvl(member.getLevel(), 1l);
    				userLevelUser=userInfo.getUserLevel();
    				//检查数据是否有变化
    				if(!(member.getMemberName().equals(userInfo.getUserAccount())
    						&& userLevelUser!=null && userLevelMember.intValue()==userLevelUser.intValue()
    						)){
    					userInfo.setUserAccount(member.getMemberName());
    					userInfo.setUserLevel(userLevelMember.intValue());
    					changeList.add(userInfo);
    				}
    			}
    		}
    		if(!isExist){
    			newList.add(member);
    		}
    	}
    	
    	logger.info("-----userInfoSync--changeList="+changeList.size()+" newList="+newList.size()+" time="+(System.currentTimeMillis()-time));
    	//保存数据有修改的数据
    	for(UserInfo userInfo:changeList){
    		this.userInfoService.updateByPrimaryKey(userInfo);
    	}
    	//保存未找到的新增数据
    	this.saveAddUserInfo(newList);
    	logger.info("-----userInfoSync--batchList--end time="+(System.currentTimeMillis()-time));
    	
    }
    
    /**
     * 保存未找到的新增数据
     * @param newList
     */
    private void saveAddUserInfo(List<MemberConditionVo> newList){
    	if(CollectionUtils.isEmpty(newList)){
    		return;
    	}
    	List<UserInfo> userList=new ArrayList<>();
    	UserInfo userInfo=null;
    	Long userLevel=null;
    	for(MemberConditionVo member:newList){
    		userInfo=new UserInfo();
    		userInfo.setCreateTime(member.getRegisterTime());
    		userInfo.setProxyId(member.getAgentId());
    		userInfo.setUserId(member.getMemberId());
    		userInfo.setUserAccount(member.getMemberName());
    		userInfo.setShareholderId(member.getStockId());
    		userInfo.setOwnerId(member.getOwnerId());
    		userLevel=(Long)CommUtils.nvl(member.getLevel(), 1l);
    		userInfo.setUserLevel(userLevel.intValue());
    		userInfo.setUpdateTime(new Date().getTime());
    		userInfo.setType(0);//用户类型，默认为0
    		userList.add(userInfo);
    	}
    	this.userInfoService.batchInsert(userList);
    }



}
