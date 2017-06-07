package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.PrizeDetailAssemService;
import com.magic.crius.po.PrizeDetail;
import com.magic.crius.service.PrizeDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:13
 */
@Service
public class PrizeDetailAssemServiceImpl implements PrizeDetailAssemService {

    @Resource
    private PrizeDetailService prizeDetailService;

    @Override
    public void batchSave(List<PrizeDetail> details) {
        prizeDetailService.batchSave(details);
    }
}
