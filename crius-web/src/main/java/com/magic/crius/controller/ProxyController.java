package com.magic.crius.controller;

import com.magic.crius.assemble.ProxyInfoAssemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/7
 * Time: 22:59
 */
@Controller
@RequestMapping("/v1/crius/proxy")
public class ProxyController {

    @Resource
    private ProxyInfoAssemService proxyInfoAssemService;

    /**
     * 纠正代理数据
     * @param startTime
     * @param endTime
     */
    @RequestMapping(value = "/rectify", method = RequestMethod.POST)
    @ResponseBody
    public void rectify(
            @RequestParam(name = "startTime",defaultValue = "0" ,required = false) Long startTime,
            @RequestParam(name = "endTime",defaultValue = "0" ,required = false) Long endTime
    ) {
        proxyInfoAssemService.batchSave(startTime, endTime);
    }
}
