package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerInfo;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:54
 */
public interface OwnerInfoDbService {

    boolean insert(OwnerInfo record);

    boolean batchInsert(List<OwnerInfo> infos);

    OwnerInfo get(Long ownerId);
}
