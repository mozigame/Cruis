package com.magic.crius.dao.mongo;

import com.magic.crius.dao.base.BaseMongoDAOImpl;
import com.magic.crius.vo.DealerRewardReq;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:52
 */
@Component
public class DealerRewardReqMongoDao  extends BaseMongoDAOImpl<DealerRewardReq> {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
