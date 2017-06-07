package com.magic.crius.service;

import com.magic.crius.po.OwnerAwardDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:33
 * 打赏明细
 */
public interface OwnerAwardDetailService {

    boolean insert(OwnerAwardDetail record);

    boolean batchInsert(List<OwnerAwardDetail> details);
}
