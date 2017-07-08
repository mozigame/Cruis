package com.magic.crius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: joey
 * Date: 2017/7/7
 * Time: 22:37
 */
@Controller
@RequestMapping("/v1/crius/member")
public class MemberController {



    @RequestMapping(value = "/rectify", method = RequestMethod.POST)
    @ResponseBody
    public void rectify(
            @RequestParam(name = "startTime",defaultValue = "0" ,required = false) Long startTime,
            @RequestParam(name = "endTime",defaultValue = "0" ,required = false) Long endTime
    ) {

    }
}
