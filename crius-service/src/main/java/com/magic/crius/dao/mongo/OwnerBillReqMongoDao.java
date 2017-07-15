package com.magic.crius.dao.mongo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.magic.crius.dao.base.BaseMongoDAOImpl;
import com.magic.crius.vo.OwnerBillReq;

/**
 * 
 * @author Justin
 * @date 2017-07-15
 *
 */
@Component
public class OwnerBillReqMongoDao  extends BaseMongoDAOImpl<OwnerBillReq> {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
