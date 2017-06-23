package com.magic.crius.service.impl;

import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.storage.mongo.MemberConditionVoMongoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:26
 */
@Service
public class MemberConditionVoServiceImpl implements MemberConditionVoService {

    @Resource
    private MemberConditionVoMongoService memberConditionVoMongoService;


    @Override
    public boolean updateRecharge(MemberConditionVo vo) {
        return memberConditionVoMongoService.updateRecharge(vo);
    }

    @Override
    public boolean updateWithdraw(MemberConditionVo vo) {
        return memberConditionVoMongoService.updateWithdraw(vo);
    }

    @Override
    public boolean updateLevel(UserLevelReq userLevelReq) {
        return memberConditionVoMongoService.updateLevel(userLevelReq);
    }
}
