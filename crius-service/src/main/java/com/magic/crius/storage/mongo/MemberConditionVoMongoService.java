package com.magic.crius.storage.mongo;

import java.util.List;

import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:59
 * 会员mongo信息
 */
public interface MemberConditionVoMongoService {

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
     * 获取一段时间内的会员的层级列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemberConditionVo> findPeriodLevels(Long startTime, Long endTime);
    
    public List<MemberConditionVo> findByPage(int page, int pageCount);
    
    /**
     * 取得表的总记录数
     * @return
     */
    public Long getTotalCount();
}
