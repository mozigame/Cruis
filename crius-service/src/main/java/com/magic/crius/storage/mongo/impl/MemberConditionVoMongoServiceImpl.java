package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.MemberConditionVoMongoDao;
import com.magic.crius.storage.mongo.MemberConditionVoMongoService;
import com.magic.user.vo.MemberConditionVo;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

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
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class);
            return result.getN() > 0;
        } catch (Exception e) {
            e.printStackTrace();
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
            WriteResult result = memberConditionVoMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class);
            return result.getN() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
