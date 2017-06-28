package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.service.MemberConditionVoService;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.vo.UserLevelReq;
import com.magic.user.vo.MemberConditionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:26
 */
@Service
public class UserLevelAssemService {

    @Resource
    private MemberConditionVoService memberConditionVoService;
    @Resource
    private UserInfoService userInfoService;


    public void updateLevel(UserLevelReq userLevelReq) {
        if (!memberConditionVoService.updateLevel(userLevelReq)) {

        }
    }

    /**
     * 批量修改一段时间内的会员层级
     *
     * @param date
     */
    public void batchUpdateLevel(Date date) {
        String hhStr = DateUtil.formatDateTime(date, "yyyyMMddHH");
        Date endTime = DateUtil.parseDate(hhStr, "yyyyMMddHH");
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.HOUR, -1);
        List<MemberConditionVo> vos = memberConditionVoService.findPeriodLevels(startTime.getTimeInMillis(), endTime.getTime());
        if (vos.size() > 0) {
            System.out.println("findPeriodLevels ,size :" + vos.size());
            int i = 0;
            for (MemberConditionVo vo : vos) {
                if (userInfoService.updateLevel(vo.getMemberId(), vo.getLevel())) {
                    i++;
                }
            }
            System.out.println("batchUpdateLevel size : " + i);
        }
    }
}
