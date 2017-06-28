package com.magic.crius.storage.mongo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.SymbolTable;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.dao.mongo.MemberConditionVoMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.MemberConditionVoMongoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.entity.Member;
import com.magic.user.vo.MemberConditionVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
                System.out.println("find memberConditionVo null, memberId :"+vo.getMemberId());
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
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class, MongoCollections.memberConditionVo.name());
            System.out.println("updateRecharge member, id:"+vo.getMemberId() + ", result : "+result.toString());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("depositMoney", vo.getDepositMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            WriteResult agentResult = memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate,WriteResult.class, MongoCollections.agentConditionVo.name());
            System.out.println("updateRecharge agent, id:"+vo.getAgentId() + ", result : "+agentResult.toString());

            return true;
        } catch (Exception e) {
            logger.error("MemberCondition capital error , vo : " + JSON.toJSONString(vo));
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
                logger.warn("find memberConditionVo null, memberId :"+vo.getMemberId());
                return false;
            }
            Update update = new Update();
            if (findVo.getMaxWithdrawMoney() < vo.getMaxWithdrawMoney()) {
                update.set("maxWithdrawMoney",vo.getMaxWithdrawMoney());
            }
            update.set("lastWithdrawMoney", vo.getLastWithdrawMoney());
            update.inc("withdrawCount", vo.getWithdrawCount());
            update.inc("withdrawMoney", vo.getWithdrawMoney());
            update.set("updateTime", System.currentTimeMillis());
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class, MongoCollections.memberConditionVo.name());
            System.out.println("updateWithdraw member, id:"+vo.getMemberId() + ", result : "+result.toString());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("withdrawMoney", vo.getWithdrawMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            WriteResult agentResult = memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate,WriteResult.class, MongoCollections.agentConditionVo.name());
            System.out.println("updateWithdraw agent, id:"+vo.getAgentId() + ", result : "+agentResult.toString());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLevel(UserLevelReq userLevelReq) {
        try {
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
            return  memberConditionVoMongoDao.find(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
