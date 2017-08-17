package com.magic.crius.controller;

import com.alibaba.dubbo.remoting.telnet.support.Help;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * User: joey
 * Date: 2017/6/28
 * Time: 0:48
 */
@Controller
@RequestMapping("/v1/crius/help")
public class HelpController {

    private static final Logger logger = Logger.getLogger(HelpController.class);

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(
            HttpServletRequest request
    ) {
        return request.getRemoteAddr() + " - " + request.getRemoteHost();
    }
}
