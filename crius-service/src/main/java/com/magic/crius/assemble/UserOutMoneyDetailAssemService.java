package com.magic.crius.assemble;

import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.service.UserOutMoneyDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 13:45
 * 会员出款明细
 */
@Service
public class UserOutMoneyDetailAssemService {

    @Resource
    private UserOutMoneyDetailService userOutMoneyDetailService;

    public void batchSave(List<UserOutMoneyDetail> details) {
        userOutMoneyDetailService.batchInsert(details);
    }
}
