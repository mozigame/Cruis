package com.magic.crius.dao.mongo;

import com.magic.crius.dao.base.BaseMongoDAOImpl;
import com.magic.user.vo.AgentConditionVo;
import com.magic.user.vo.MemberConditionVo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:01
 */
@Component
public class AgentConditionVoMongoDao extends BaseMongoDAOImpl<AgentConditionVo> {

    @Resource(name = "mongoTemplateAccount")
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
