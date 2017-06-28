package com.magic.crius.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.mq.annotation.ConsumerConfig;
import com.magic.api.commons.mq.api.Consumer;
import com.magic.api.commons.mq.api.Topic;
import com.magic.crius.po.OwnerInfo;
import com.magic.crius.service.OwnerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/19
 * Time: 16:52
 * 添加业主信息
 */
@Service("masterAddOwnerSuccessConsumer")
@ConsumerConfig(consumerName = "v1criusMasterAddOwnerSuccessConsumer", topic = Topic.BC_COMPANY_ADD_SUCCESS)
public class MasterAddOwnerSuccessConsumer implements Consumer {

    @Resource
    private OwnerInfoService ownerInfoService;

    @Override
    public boolean doit(String topic, String tags, String key, String msg) {
        ApiLogger.info(String.format("master control add owner success mq consumer start. key:%s, msg:%s", key, msg));
        try {
            JSONObject object = JSONObject.parseObject(msg);
            long ownerId = object.getLongValue("id");
            String ownerName = object.getString("account");
            String siteName = object.getString("siteName");
            OwnerInfo ownerInfo = new OwnerInfo();
            ownerInfo.setOwnerId(ownerId);
            ownerInfo.setOwnerName(ownerName);
            ownerInfo.setSiteId(0L);
            ownerInfo.setSiteName(siteName);
            if (!ownerInfoService.insert(ownerInfo)) {
                if (ownerInfoService.get(ownerId) == null) {
                    return false;
                }
            }
        } catch (Exception e) {
            ApiLogger.error(String.format("master control add owner success mq consumer error. key:%s, msg:%s", key, msg), e);
        }
        return true;
    }




}
