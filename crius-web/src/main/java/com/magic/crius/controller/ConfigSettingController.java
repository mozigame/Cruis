package com.magic.crius.controller;

import com.magic.crius.service.BaseReqService;
import com.magic.crius.storage.redis.BaseReqRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/delScheduleSwitch", method = RequestMethod.POST)
    @ResponseBody
    public String delScheduleSwitch() {
        baseReqService.delScheduleSwitch();
        return "true";
    }
}
