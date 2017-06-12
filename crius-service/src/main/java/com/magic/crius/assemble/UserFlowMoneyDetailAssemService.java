package com.magic.crius.assemble;

import com.magic.crius.assemble.UserFlowMoneyDetailAssemService;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.service.UserFlowMoneyDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:11
 */
@Service
public class UserFlowMoneyDetailAssemService {

    @Resource
    private UserFlowMoneyDetailService userFlowMoneyDetailService;

    public boolean batchSave(List<UserFlowMoneyDetail> details) {
        return userFlowMoneyDetailService.batchSave(details);
    }
}
