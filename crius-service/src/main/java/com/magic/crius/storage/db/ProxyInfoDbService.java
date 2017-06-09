package com.magic.crius.storage.db;

import com.magic.crius.po.ProxyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:50
 */
public interface ProxyInfoDbService {

    boolean batchInsert(List<ProxyInfo> infos);
}
