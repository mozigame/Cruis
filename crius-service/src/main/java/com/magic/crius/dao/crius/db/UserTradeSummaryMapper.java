package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserTradeSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface UserTradeSummaryMapper {

    int batchInsert(@Param("list") List<UserTradeSummary> summaries);

    int update(UserTradeSummary summary);

    List<UserTradeSummary> getSummaryTypeList(@Param("list") Collection<UserTradeSummary> summaries);

}