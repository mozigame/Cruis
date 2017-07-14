package com.magic.crius.dao.crius.db;

import com.magic.crius.po.ProxyBillSummary2game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProxyBillSummary2gameMapper {

    int insert(ProxyBillSummary2game record);

    int batchInsert(List<ProxyBillSummary2game> proxyBillSummary2gameList);
}