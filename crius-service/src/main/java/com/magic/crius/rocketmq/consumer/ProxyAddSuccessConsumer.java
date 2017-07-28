package com.magic.crius.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.mq.annotation.ConsumerConfig;
import com.magic.api.commons.mq.api.Consumer;
import com.magic.api.commons.mq.api.Topic;
import com.magic.crius.po.OwnerInfo;
import com.magic.crius.po.ProxyInfo;
import com.magic.crius.service.OwnerInfoService;
import com.magic.crius.service.ProxyInfoService;
import com.magic.user.vo.AgentConditionVo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加代理
 */
@Service("proxyAddSuccessConsumer")
@ConsumerConfig(consumerName = "v1criusProxyAddSuccessConsumer", topic = Topic.AGENT_ADD_SUCCESS)
public class ProxyAddSuccessConsumer implements Consumer {


    private static final Logger logger = Logger.getLogger(ProxyAddSuccessConsumer.class);

    @Resource
    private ProxyInfoService proxyInfoService;

    @Resource
    private OwnerInfoService ownerInfoService;

    @Override
    public boolean doit(String topic, String tags, String key, String msg) {
        logger.info(String.format("proxy control add  mq consumer start. key:%s, msg:%s", key, msg));
        try {
            JSONObject object = JSONObject.parseObject(msg);
            String agentConditionStr = object.getString("AGENT_CONDITION_ADD");
            if (StringUtils.isBlank(agentConditionStr)) {
                ApiLogger.error("agent add success mq consumer no agentConfigStr or userIdMappingStr");
                return false;
            }
            AgentConditionVo agentConditionVo = JSONObject.parseObject(agentConditionStr, AgentConditionVo.class);
            if (agentConditionVo != null ){
                List<ProxyInfo> proxyInfos = new ArrayList<>();
                ProxyInfo proxyInfo = new ProxyInfo();
                if(StringUtils.isNotEmpty(agentConditionVo.getId()))
                    proxyInfo.setId(Long.parseLong(agentConditionVo.getId()));

                proxyInfo.setOwnerId(agentConditionVo.getOwnerId());
                // 获取股东名称
                OwnerInfo ownerInfo = ownerInfoService.get(agentConditionVo.getOwnerId());
                if (ownerInfo != null)
                    proxyInfo.setShareholderName(ownerInfo.getOwnerName());

                proxyInfo.setProxyId(agentConditionVo.getAgentId());
                proxyInfo.setProxyName(agentConditionVo.getAgentName());
                proxyInfo.setShareholderId(agentConditionVo.getStockId());

                proxyInfos.add(proxyInfo);
                proxyInfoService.batchInsert(proxyInfos);
            }

        } catch (Exception e) {
            logger.error(String.format("proxy control add owner success mq consumer error. key:%s, msg:%s", key, msg), e);
        }
        return true;
    }




}
