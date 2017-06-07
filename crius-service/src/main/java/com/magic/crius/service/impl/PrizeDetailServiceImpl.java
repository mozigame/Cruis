package com.magic.crius.service.impl;

import com.magic.crius.po.PrizeDetail;
import com.magic.crius.service.PrizeDetailService;
import com.magic.crius.storage.db.PrizeDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:12
 */
@Service
public class PrizeDetailServiceImpl implements PrizeDetailService {

    @Resource
    private PrizeDetailDbService prizeDetailDbService;

    @Override
    public boolean save(PrizeDetail detail) {
        return prizeDetailDbService.save(detail);
    }

    @Override
    public boolean batchSave(List<PrizeDetail> details) {
        return prizeDetailDbService.batchSave(details);
    }
}
