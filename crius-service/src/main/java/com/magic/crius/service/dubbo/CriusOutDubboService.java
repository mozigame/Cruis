package com.magic.crius.service.dubbo;

import com.magic.user.entity.User;
import com.magic.user.service.dubbo.AccountDubboService;
import org.springframework.stereotype.Component;

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

    private AccountDubboService accountDubboService;

    /**
     * 获取账号系统中时间段内的代理数据
     * @param startTime
     * @param endTime
     * @return
     */
    public List<User> getDateAgents(Long startTime, Long endTime) {
        return null;
    }
}
