package com.magic.crius.storage.mongo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.dao.mongo.MemberConditionVoMongoDao;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.db.SpringDataPageable;
import com.magic.crius.storage.mongo.MemberConditionVoMongoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.AgentConditionVo;
import com.magic.user.vo.MemberConditionVo;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:01
 * 会员mongo资金信息
 */
@Service
public class MemberConditionVoMongoServiceImpl implements MemberConditionVoMongoService {

    private static final Logger logger = Logger.getLogger(MemberConditionVoMongoServiceImpl.class);

    @Resource
    private MemberConditionVoMongoDao memberConditionVoMongoDao;

    @Override
    public boolean updateRecharge(MemberConditionVo vo) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("memberId").is(vo.getMemberId()));

            MemberConditionVo findVo = memberConditionVoMongoDao.findOne(query);
            if (findVo == null) {
                ApiLogger.warn("find memberConditionVo null, memberId :" + vo.getMemberId());
                return false;
            }
            Update update = new Update();
            if (findVo.getMaxDepositMoney() < vo.getMaxDepositMoney()) {
                update.set("maxDepositMoney", vo.getMaxDepositMoney());
            }
            update.set("lastDepositMoney", vo.getLastDepositMoney());
            update.inc("depositCount", vo.getDepositCount());
            update.inc("depositMoney", vo.getDepositMoney());
            update.set("updateTime", System.currentTimeMillis());
            memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, MemberConditionVo.class, MongoCollections.memberConditionVo.name());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("depositMoney", vo.getDepositMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate, AgentConditionVo.class, MongoCollections.agentConditionVo.name());
            return true;
        } catch (Exception e) {
            logger.error("MemberCondition capital error , vo : " + JSON.toJSONString(vo));
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateWithdraw(MemberConditionVo vo) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("memberId").is(vo.getMemberId()));
            MemberConditionVo findVo = memberConditionVoMongoDao.findOne(query);
            if (findVo == null) {
                logger.warn("find memberConditionVo null, memberId :" + vo.getMemberId());
                return false;
            }
            Update update = new Update();
            if (findVo.getMaxWithdrawMoney() < vo.getMaxWithdrawMoney()) {
                update.set("maxWithdrawMoney", vo.getMaxWithdrawMoney());
            }
            update.set("lastWithdrawMoney", vo.getLastWithdrawMoney());
            update.inc("withdrawCount", vo.getWithdrawCount());
            update.inc("withdrawMoney", vo.getWithdrawMoney());
            update.set("updateTime", System.currentTimeMillis());
            memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, MemberConditionVo.class, MongoCollections.memberConditionVo.name());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("withdrawMoney", vo.getWithdrawMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate, AgentConditionVo.class, MongoCollections.agentConditionVo.name());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLevel(UserLevelReq userLevelReq) {
        try {
            ApiLogger.info("update userLevel , memberId : " + userLevelReq.getUserId() + ", levelId : " + userLevelReq.getLevelId());
            Query query = new Query(new Criteria("memberId").is(userLevelReq.getUserId()));
            Update update = new Update();
            update.set("level", userLevelReq.getLevelId());
            update.set("updateTime", System.currentTimeMillis());
            return memberConditionVoMongoDao.update(query, update) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MemberConditionVo> findPeriodLevels(Long startTime, Long endTime) {
        try {
            Query query = new Query(new Criteria("updateTime").gte(startTime).lt(endTime));
            return memberConditionVoMongoDao.find(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public List<MemberConditionVo> findByPage(int page, int pageCount) {
        try {
        	SpringDataPageable pageable = new SpringDataPageable();
        	
        	List<Order> orders = new ArrayList<Order>();  //排序
            orders.add(new Order(Direction.ASC, "_id"));
            Sort sort = new Sort(orders);

            // 开始页
            pageable.setPagenumber(page);
            // 每页条数
            pageable.setPagesize(pageCount);
            // 排序
            pageable.setSort(sort);
            Query query = new Query();
            List<MemberConditionVo> list = memberConditionVoMongoDao.find(query.with(pageable), MongoCollections.memberConditionVo.name());
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 取得表的总记录数
     * @return
     */
    public Long getTotalCount(){
    	Query query = new Query();
    	return memberConditionVoMongoDao.count(query);
    }
}
