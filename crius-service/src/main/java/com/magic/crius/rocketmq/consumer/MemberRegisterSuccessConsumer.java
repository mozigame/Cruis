package com.magic.crius.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.mq.annotation.ConsumerConfig;
import com.magic.api.commons.mq.api.Consumer;
import com.magic.api.commons.mq.api.Topic;
import com.magic.crius.po.UserInfo;
import com.magic.crius.service.UserInfoService;
import com.magic.user.entity.Member;
import com.magic.user.enums.AccountType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 11:10
 */
@Component("memberRegisterSucessMongoConsumer")
@ConsumerConfig(consumerName = "v1criusMemberRegisterSucessMongoConsumer", topic = Topic.MEMBER_REGISTER_SUCCESS)
public class MemberRegisterSuccessConsumer implements Consumer {

    @Resource
    private UserInfoService userInfoService;

    @Override
    public boolean doit(String topic, String tags, String key, String msg) {
        ApiLogger.info(String.format("member register sucess mongo mq consumer start. key:%s, msg:%s", key, msg));
        try {
            Member member = JSONObject.parseObject(msg, Member.class);
            UserInfo userInfo = assembleUseInfo(member);
            if (!userInfoService.save(userInfo)) {
                if (userInfoService.get(userInfo.getUserId()) != null) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            ApiLogger.error(String.format("member register sucess mq consumer error. key:%s, msg:%s", key, msg), e);
        }
        return true;
    }

    /**
     * 组装会员数据
     * @param member
     * @return
     */
    private UserInfo assembleUseInfo(Member member) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(member.getMemberId());
        userInfo.setUserAccount(member.getUsername());
        userInfo.setOwnerId(member.getOwnerId());
        userInfo.setOwnerName(member.getOwnerUsername());
        userInfo.setShareholderId(member.getStockId());
        userInfo.setShareholderName(member.getStockUsername());
        userInfo.setProxyId(member.getAgentId());
        userInfo.setProxyName(member.getAgentUsername());
        userInfo.setCreateTime(member.getRegisterTime());
        userInfo.setUpdateTime(member.getRegisterTime());
        //todo 会员层级待确定，或许默认0
        userInfo.setUserLevel(0);
        userInfo.setType(AccountType.member.value());
        return userInfo;
    }
}
