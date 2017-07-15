package com.magic.crius.service;

import com.magic.crius.vo.UserLevelReq;
import com.magic.user.bean.MemberCondition;
import com.magic.user.vo.MemberConditionVo;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 21:26
 */
public interface MemberConditionVoService {

    /**
     * 修改入款信息
     * @param vo
     * @return
     */
    boolean updateRecharge(MemberConditionVo vo);

    /**
     * 修改出款信息
     * @param vo
     * @return
     */
    boolean updateWithdraw(MemberConditionVo vo);

    /**
     * 修改会员层级
     * @param userLevelReq
     * @return
     */
    boolean updateLevel(UserLevelReq userLevelReq);

    /**
     * 获取一段时间内的会员
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemberConditionVo> findPeriodLevels(Long startTime, Long endTime);
    
    /**
     * 翻页查询
     * @param page
     * @param pageCount
     * @return
     */
    public List<MemberConditionVo> findByPage(int page, int pageCount);
    
    /**
     * 取得表的总记录数
     * @return
     */
    public Long getTotalCount();
    
}
