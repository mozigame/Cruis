package com.magic.crius.dao.crius.db;

import com.magic.crius.po.UserTradeSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface UserTradeSummaryMapper {

    int batchInsert(@Param("list") Collection<UserTradeSummary> summaries);

    int update(UserTradeSummary summary);

    List<UserTradeSummary> getSummaryTypeList(@Param("list") Collection<UserTradeSummary> summaries,@Param("ownerId") Long ownerId,@Param("summaryType") Integer summaryType );

}