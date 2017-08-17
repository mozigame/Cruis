package com.magic.crius.controller;

import com.magic.crius.assemble.UserPreferentialDetailAssemService;
import com.magic.crius.service.impl.UserPreferentialDetailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * created by roachjiang ON 2017/8/17
 */
@Controller
@RequestMapping("/v1/crius/discount")
public class DiscountController {


    private static final Logger logger = Logger.getLogger(HelpController.class);

    @Resource
    private UserPreferentialDetailAssemService userPreferentialDetailAssemService;

    @RequestMapping(value = "/repair/userPreferential", method = RequestMethod.GET)
    @ResponseBody
    public String repairUserPreferential(
            HttpServletRequest request,
            @RequestParam(name = "idList", required = true) String idList
    ) {
        return userPreferentialDetailAssemService.repairUserPreferential(idList);
    }
}
