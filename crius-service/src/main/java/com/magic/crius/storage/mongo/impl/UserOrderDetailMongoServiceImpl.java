package com.magic.crius.storage.mongo.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.dao.mongo.UserOrderDetailMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.storage.mongo.UserOrderDetailMongoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 17:28
 */
@Service
public class UserOrderDetailMongoServiceImpl implements UserOrderDetailMongoService {


    @Resource
    private UserOrderDetailMongoDao userOrderDetailMongoDao;

    @Override
    public boolean saveUpdateFailed(UserOrderDetail detail) {
        try {
            return userOrderDetailMongoDao.save(detail, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.userOrderDetail.name())) != null;
        } catch (Exception e) {
            ApiLogger.error("save userOrderDetail mongo error, detail : " + JSON.toJSONString(detail), e);
        }
        return false;
    }
}
