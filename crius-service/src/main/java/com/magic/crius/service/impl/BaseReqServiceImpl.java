package com.magic.crius.service.impl;

import com.magic.crius.service.BaseReqService;
import com.magic.crius.storage.redis.BaseReqRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/15
 * Time: 10:39
 */
@Service
public class BaseReqServiceImpl implements BaseReqService{

    @Resource
    private BaseReqRedisService baseReqRedisService;

    @Override
    public int getNoProcPage(String key) {
        return baseReqRedisService.getNoProcPage(key);
    }

    @Override
    public boolean getScheduleSwitch() {
        return baseReqRedisService.getScheduleSwitch();
    }

    @Override
    public void setScheduleSwitch() {
        baseReqRedisService.setScheduleSwitch();
    }

    @Override
    public void delScheduleSwitch() {
        baseReqRedisService.delScheduleSwitch();
    }
}
