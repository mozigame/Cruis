package com.magic.crius.assemble;

import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.vo.*;
import com.magic.user.vo.MemberConditionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:27
 */
@Service
public class MemberConditionVoAssemService {

    @Resource
    private MemberConditionVoService memberConditionVoService;

    /**
     * 充值
     * @param vos
     */
    public void batchRecharge(Collection<MemberConditionVo> vos) {
        if (vos != null && vos.size() > 0) {
            for (MemberConditionVo vo : vos) {
                //todo 如果失败如何处理
                if (!memberConditionVoService.updateRecharge(vo)) {

                }
            }
        }
    }

    /**
     * 取款
     * @param vos
     */
    public void batchWithdraw(Collection<MemberConditionVo> vos) {
        if (vos != null && vos.size() > 0) {
            for (MemberConditionVo vo : vos) {
                //todo 如果失败如何处理
                if (!memberConditionVoService.updateWithdraw(vo)) {

                }
            }
        }
    }

    /*会员存款*/
    public MemberConditionVo assembleDepositMVo(OnlChargeReq req) {
        MemberConditionVo vo = new MemberConditionVo();
        vo.setMemberId(req.getUserId());
        vo.setAgentId(req.getAgentId());
        vo.setDepositCount(1);
        vo.setDepositMoney(req.getChargeAmount());
        vo.setLastDepositMoney(req.getChargeAmount());
        vo.setMaxDepositMoney(req.getChargeAmount());
        return vo;
    }
    /*会员存款*/
    public MemberConditionVo assembleDepositMVo(PreCmpChargeReq req) {
        MemberConditionVo vo = new MemberConditionVo();
        vo.setMemberId(req.getUserId());
        vo.setAgentId(req.getAgentId());
        vo.setDepositCount(1);
        vo.setDepositMoney(req.getChargeAmount());
        vo.setLastDepositMoney(req.getChargeAmount());
        vo.setMaxDepositMoney(req.getChargeAmount());
        return vo;
    }
    /*会员存款*/
    public MemberConditionVo assembleDepositMVo(OperateChargeReq req, Long userId) {
        MemberConditionVo vo = new MemberConditionVo();
        vo.setMemberId(userId);
        vo.setAgentId(req.getAgentId());
        vo.setDepositCount(1);
        vo.setDepositMoney(req.getChargeAmount());
        vo.setLastDepositMoney(req.getChargeAmount());
        vo.setMaxDepositMoney(req.getChargeAmount());
        return vo;
    }

    /*会员提款*/
    public MemberConditionVo assembleWithdrawMVo(PreWithdrawReq req) {
        MemberConditionVo vo = new MemberConditionVo();
        vo.setMemberId(req.getUserId());
        vo.setAgentId(req.getAgentId());
        vo.setWithdrawCount(1);
        vo.setWithdrawMoney(req.getRealWithdrawAmount());
        vo.setLastWithdrawMoney(req.getRealWithdrawAmount());
        vo.setMaxWithdrawMoney(req.getRealWithdrawAmount());
        return vo;
    }
    /*人工提款*/
    public MemberConditionVo assembleWithdrawMVo(OperateWithDrawReq req, Long userId) {
        MemberConditionVo vo = new MemberConditionVo();
        vo.setMemberId(userId);
        //todo 不确定是否需要
//        vo.setAgentId(req.getAgentId());
        vo.setWithdrawCount(1);
        vo.setWithdrawMoney(req.getAmount());
        vo.setLastWithdrawMoney(req.getAmount());
        vo.setMaxWithdrawMoney(req.getAmount());
        return vo;
    }

}
