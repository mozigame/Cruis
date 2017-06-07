package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerAwardDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:32
 * 打赏明细
 */
public interface OwnerAwardDetailDbService {

    boolean insert(OwnerAwardDetail record);

    boolean batchInsert(List<OwnerAwardDetail> details);


}
