package com.magic.crius.assemble;

import com.magic.crius.po.BillInfo;
import com.magic.crius.service.BillInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:18
 */
@Service
public class BillInfoAssemService {

    @Resource
    private BillInfoService billInfoService;


    public boolean save(BillInfo billInfo) {
        return billInfoService.save(billInfo);
    }

}
