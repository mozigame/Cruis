package com.magic.crius.dao.mongo;

import com.magic.crius.dao.base.BaseMongoDAOImpl;
import com.magic.crius.vo.SportReq;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:02
 */
@Component
public class SportReqMongoDao extends BaseMongoDAOImpl<SportReq> {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
