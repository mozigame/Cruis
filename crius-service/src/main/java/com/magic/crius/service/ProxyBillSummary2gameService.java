package com.magic.crius.service;

import com.magic.crius.po.ProxyBillSummary2game;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:40
 */
public interface ProxyBillSummary2gameService {

    boolean save(ProxyBillSummary2game game);

    boolean batchInsert(List<ProxyBillSummary2game> gameList);
}
