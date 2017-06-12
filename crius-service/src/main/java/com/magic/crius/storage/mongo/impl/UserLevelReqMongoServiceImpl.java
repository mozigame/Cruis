package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.UserLevelReqMongoDao;
import com.magic.crius.storage.mongo.UserLevelReqMongoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.bean.MemberCondition;
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
 * Time: 20:09
 */
@Service
public class UserLevelReqMongoServiceImpl implements UserLevelReqMongoService{

    @Resource
    private UserLevelReqMongoDao userLevelReqMongoDao;
    @Override
    public boolean updateLevel(UserLevelReq userLevelReq) {
        try {
            Query query  =new Query(new Criteria("memberId").is(userLevelReq.getUserId()));
            Update update = new Update();
            update.set("level",userLevelReq.getLevelId());
            WriteResult result = userLevelReqMongoDao.getMongoTemplate().findAndModify(query, update, WriteResult.class);
            return result.getN() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
