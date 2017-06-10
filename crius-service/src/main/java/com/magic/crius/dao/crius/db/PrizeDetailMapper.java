package com.magic.crius.dao.crius.db;

import com.magic.crius.po.PrizeDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 彩金明细
 */
@Component
public interface PrizeDetailMapper {


    int insert(PrizeDetail record);

    int batchInsert(List<PrizeDetail> record);
}