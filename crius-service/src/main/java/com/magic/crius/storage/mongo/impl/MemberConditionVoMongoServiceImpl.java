package com.magic.crius.storage.mongo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.SymbolTable;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.dao.mongo.MemberConditionVoMongoDao;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.MemberConditionVoMongoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:01
 * 会员mongo资金信息
 */
@Service
public class MemberConditionVoMongoServiceImpl implements MemberConditionVoMongoService {

    @Resource
    private MemberConditionVoMongoDao memberConditionVoMongoDao;

    @Override
    public boolean updateRecharge(MemberConditionVo vo) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("memberId").is(vo.getMemberId()));
            Update update = new Update();
            update.inc("depositCount", vo.getDepositCount());
            update.inc("depositMoney", vo.getDepositMoney());
            update.set("updateTime", System.currentTimeMillis());
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class, MongoCollections.memberConditionVo.name());
            ApiLogger.info("updateRecharge member, id:"+vo.getMemberId() + ", result : "+result.toString());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("depositMoney", vo.getDepositMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            WriteResult agentResult = memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate,WriteResult.class, MongoCollections.agentConditionVo.name());
            ApiLogger.info("updateRecharge agent, id:"+vo.getAgentId() + ", result : "+agentResult.toString());

            return true;
        } catch (Exception e) {
            System.out.println("MemberCondition capital error , vo : " + JSON.toJSONString(vo));
        }
        return false;
    }

    @Override
    public boolean updateWithdraw(MemberConditionVo vo) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("memberId").is(vo.getMemberId()));
            Update update = new Update();
            update.inc("withdrawCount", vo.getWithdrawCount());
            update.inc("withdrawMoney", vo.getWithdrawMoney());
            update.set("updateTime", System.currentTimeMillis());
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class, MongoCollections.memberConditionVo.name());
            ApiLogger.info("updateWithdraw member, id:"+vo.getMemberId() + ", result : "+result.toString());

            Query agentQ = new Query();
            agentQ.addCriteria(new Criteria("agentId").is(vo.getAgentId()));
            Update agentUpdate = new Update();
            agentUpdate.inc("withdrawMoney", vo.getWithdrawMoney());
            agentUpdate.set("updateTime", System.currentTimeMillis());
            WriteResult agentResult = memberConditionVoMongoDao.getMongoTemplate().findAndModify(agentQ, agentUpdate,WriteResult.class, MongoCollections.agentConditionVo.name());
            ApiLogger.info("updateWithdraw agent, id:"+vo.getAgentId() + ", result : "+agentResult.toString());

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
}
