package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.PrizeDetailMapper;
import com.magic.crius.po.PrizeDetail;
import com.magic.crius.storage.db.PrizeDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:10
 */
@Service
public class PrizeDetailDbServiceImpl implements PrizeDetailDbService {

    @Resource
    private PrizeDetailMapper prizeDetailMapper;

    @Override
    public boolean save(PrizeDetail detail) {
        return prizeDetailMapper.insert(detail) > 0;
    }

    @Override
    public boolean batchSave(List<PrizeDetail> details) {
        return prizeDetailMapper.batchInsert(details) > 0;
    }
}
