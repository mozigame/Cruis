package com.magic.crius.assemble;

import com.magic.analysis.enums.ActionType;
import com.magic.analysis.enums.SummaryType;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.po.UserTradeSummary;
import com.magic.crius.service.UserTradeSummaryService;
import com.magic.crius.vo.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: joey
 * Date: 2017/8/10
 * Time: 14:32
 */
@Component
public class UserTradeSummaryAssemService {

    @Resource
    private UserTradeSummaryService userTradeSummaryService;

    public boolean batchSave(Map<Long, UserTradeSummary> summaries) {
        try {
            List<UserTradeSummary> existList = userTradeSummaryService.getSummaryTypeList(summaries.values());
            List<UserTradeSummary> noExistList = new ArrayList<>();
            Set<Long> userIds = summaries.keySet();

            for (UserTradeSummary summary : existList) {
                if (userIds.contains(summary.getUserId())) {
                    if (summary.getMaxMoney() < summaries.get(summary.getUserId()).getMaxMoney()) {
                        summary.setMaxMoney(summaries.get(summary.getUserId()).getMaxMoney());
                    } else {
                        noExistList.add(summaries.get(summary.getUserId()));
                    }
                }
            }

            int updateCount = 0;
            for (UserTradeSummary summary : existList) {
                boolean updateResult = userTradeSummaryService.update(summary);
                updateCount += updateResult ? 1 : 0;
            }
            ApiLogger.info("update userTradeSummary count : " + updateCount);

            boolean insertResult = userTradeSummaryService.batchInsert(noExistList);
            ApiLogger.info(String.format("insert userTradeSummary count : %d, result : %b ", noExistList.size(), insertResult));
            return true;
        } catch (Exception e) {
            ApiLogger.error("batch save userTrade summary error:", e);
        }
        return false;
    }


    /*  -----------存入 --------*/

    /**
     * 线上入款
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(OnlChargeReq req) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(req.getUserId());
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.ONLINE_CHARGE.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getChargeAmount());
        summary.setLastMoney(req.getChargeAmount());
        summary.setMaxMoney(req.getChargeAmount());
        return summary;
    }

    /**
     * 公司入款
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(PreCmpChargeReq req) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(req.getUserId());
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.COMPANY_INCOME.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getChargeAmount());
        summary.setLastMoney(req.getChargeAmount());
        summary.setMaxMoney(req.getChargeAmount());
        return summary;
    }

    /**
     * 人工入款
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(OperateChargeReq req, Long userId) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(userId);
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.ARTIFICIAL_INTO_MOENY.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getChargeAmount());
        summary.setLastMoney(req.getChargeAmount());
        summary.setMaxMoney(req.getChargeAmount());
        return summary;
    }

    /**
     * 给予优惠
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(DiscountReq req) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(req.getUserId());
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.PREFERENCE.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getOfferAmount());
        summary.setLastMoney(req.getOfferAmount());
        summary.setMaxMoney(req.getOfferAmount());
        return summary;
    }


    /*  -----------提出 --------*/

    /**
     * 会员出款
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(PreWithdrawReq req) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(req.getUserId());
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.USER_OUT_MONEY.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getRealWithdrawAmount());
        summary.setLastMoney(req.getRealWithdrawAmount());
        summary.setMaxMoney(req.getRealWithdrawAmount());
        return summary;
    }


    /**
     * 人工出款
     *
     * @param req
     * @return
     */
    public UserTradeSummary assembleUserTradeSummary(OperateWithDrawReq req, Long userId) {
        UserTradeSummary summary = new UserTradeSummary();
        long time = System.currentTimeMillis();
        summary.setCreateTime(time);
        summary.setUpdateTime(time);
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(userId);
        summary.setFlowType(ActionType.CHONG_ZHI.getStatus());
        summary.setSummaryType(SummaryType.ARTIFICIAL_WITHDRAWAL.getStatus());
        summary.setTotalCount(1);
        summary.setTotalMoney(req.getAmount());
        summary.setLastMoney(req.getAmount());
        summary.setMaxMoney(req.getAmount());
        return summary;
    }
}
