package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerAwardDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 打赏明细
 */
@Component
public interface OwnerAwardDetailMapper {

    int insert(OwnerAwardDetail record);

    int batchInsert(List<OwnerAwardDetail> record);

}