package com.magic.crius.assemble;

import com.magic.crius.service.UserLevelReqService;
import com.magic.crius.vo.UserLevelReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:26
 */
@Service
public class UserLevelAssemService {

    @Resource
    private UserLevelReqService userLevelReqService;


    public void updateLevel(UserLevelReq userLevelReq) {
        if (!userLevelReqService.updateLevel(userLevelReq)) {

        }
    }
}
