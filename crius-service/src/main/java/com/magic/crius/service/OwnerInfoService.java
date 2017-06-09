package com.magic.crius.service;

import com.magic.crius.po.OwnerInfo;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:55
 */
public interface OwnerInfoService {

    boolean insert(OwnerInfo record);

    boolean batchInsert(List<OwnerInfo> infos);

    OwnerInfo get(Long ownerId);
}
