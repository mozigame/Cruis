package com.magic.crius.dao.crius.db;

import com.magic.crius.po.ProxyBillSummary2cost;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProxyBillSummary2costMapper {

    int insert(ProxyBillSummary2cost record);

    int batchInsert(List<ProxyBillSummary2cost> costs);
}