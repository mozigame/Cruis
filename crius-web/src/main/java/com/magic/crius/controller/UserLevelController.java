package com.magic.crius.controller;

import com.magic.crius.assemble.UserLevelAssemService;
import com.magic.crius.vo.UserLevelReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User: joey
 * Date: 2017/7/7
 * Time: 21:50
 */
@Controller
@RequestMapping("/v1/crius/userLevel")
public class UserLevelController {

    @Resource
    private UserLevelAssemService userLevelAssemService;

    @ResponseBody
    @RequestMapping(value = "/rectify", method = RequestMethod.POST)
    public void rectifyUserLevel(
            @RequestParam(name = "startUpdateTime", defaultValue = "0", required = false) Long startUpdateTime,
            @RequestParam(name = "endUpdateTime", defaultValue = "0", required = false) Long endUpdateTime
    ) {
        userLevelAssemService.rectifyLevel(startUpdateTime, endUpdateTime);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUserLevel(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "level") Long level
    ) {
        UserLevelReq req = new UserLevelReq();
        req.setUserId(userId);
        req.setLevelId(level);
        userLevelAssemService.updateLevel(req);
    }
}
