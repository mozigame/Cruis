package com.magic.crius.controller;

import com.magic.crius.service.BaseReqService;
import com.magic.crius.storage.redis.BaseReqRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/8/16
 * Time: 16:27
 */
@Controller
@RequestMapping("/v1/crius/config")
public class ConfigSettingController {


    @Resource
    private BaseReqService baseReqService;

    @RequestMapping("/delScheduleSwitch")
    public void delScheduleSwitch() {
        baseReqService.delScheduleSwitch();
    }
}
