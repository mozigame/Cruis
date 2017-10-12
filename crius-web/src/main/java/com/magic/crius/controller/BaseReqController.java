package com.magic.crius.controller;

import com.magic.crius.service.BaseReqService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/10/12
 * Time: 14:37
 */
@RestController
@RequestMapping("/")
public class BaseReqController {

    @Resource
    private BaseReqService baseReqService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/base/req/set_order_init_id", method = RequestMethod.POST)
    public String setInitOrderId(@RequestParam("id") Long id) {
        return baseReqService.setOrderInitId(id);
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/base/req/get_order_id", method = RequestMethod.GET)
    public String getOrderId() {
        return baseReqService.getOrderReqId()+"";
    }
}
