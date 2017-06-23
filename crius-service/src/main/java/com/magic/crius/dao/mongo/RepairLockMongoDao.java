package com.magic.crius.dao.mongo;

import com.magic.crius.dao.base.BaseMongoDAOImpl;
import com.magic.crius.po.RepairLock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 11:31
 */
@Component
public class RepairLockMongoDao extends BaseMongoDAOImpl<RepairLock> {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

}
