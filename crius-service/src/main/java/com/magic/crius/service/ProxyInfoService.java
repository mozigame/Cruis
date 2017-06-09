package com.magic.crius.service;

import com.magic.crius.po.ProxyInfo;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:52
 */
public interface ProxyInfoService {

    boolean batchInsert(List<ProxyInfo> infos);
}
