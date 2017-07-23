package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.po.ProxyInfo;
import com.magic.crius.service.ProxyInfoService;
import com.magic.crius.service.dubbo.CriusOutDubboService;
import com.magic.user.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 15:16
 */
@Service
public class ProxyInfoAssemService {

    private static Logger logger = Logger.getLogger(ProxyInfoAssemService.class);
    @Resource
    private CriusOutDubboService criusOutDubboService;
    @Resource
    private ProxyInfoService proxyInfoService;


    public void init(Date date) {
        String hhStr = DateUtil.formatDateTime(date, "yyyyMMddHH");
        Date endTime = DateUtil.parseDate(hhStr, "yyyyMMddHH");
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(DateUtil.parseDate(hhStr, "yyyyMMddHH"));
        startTime.add(Calendar.HOUR, -1);
        batchSave(startTime.getTimeInMillis(), endTime.getTime());
    }

    public void batchSave(Long startTime, Long endTime) {
        List<ProxyInfo> proxyInfos = new ArrayList<>();
        List<User> list = criusOutDubboService.getDateAgents(startTime, endTime); //获取账号系统中某个时间内的代理
        if (list != null && list.size() > 0) {
            logger.info("get proxyInfoList ,size : " + list.size());
            for (User user : list) {
                ProxyInfo proxyInfo = new ProxyInfo();
                proxyInfo.setProxyId(user.getUserId());
                proxyInfo.setProxyName(user.getUsername());
                proxyInfo.setShareholderId(user.getOwnerId());
                proxyInfo.setShareholderName(user.getOwnerName());
                proxyInfo.setOwnerId(user.getOwnerId());
                proxyInfo.setOwnerName(user.getOwnerName());
                proxyInfos.add(proxyInfo);
            }
            //todo
            if (!proxyInfoService.batchInsert(proxyInfos)) {

            }
        }
    }


}
