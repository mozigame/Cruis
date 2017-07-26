package com.magic.crius.service.dubbo;

import com.magic.user.entity.User;
import com.magic.user.service.dubbo.AccountDubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 15:22
 * 统一管理调用外部的dubbo
 */
@Component
public class CriusOutDubboService {

    @Resource
    private AccountDubboService accountDubboService;

    /**
     * 获取账号系统中时间段内的代理数据
     * @param startTime
     * @param endTime
     * @return
     */
    public List<User> getDateAgents(Long startTime, Long endTime) {
        try {
            return accountDubboService.periodAgentList(startTime, endTime, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<User> getAgentListByPage(Integer offset, Integer count){
    	return accountDubboService.getAgentListByPage(offset, count);
    }
}
