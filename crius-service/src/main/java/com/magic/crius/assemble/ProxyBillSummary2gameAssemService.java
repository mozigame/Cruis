package com.magic.crius.assemble;

import com.magic.crius.po.ProxyBillSummary2game;
import com.magic.crius.service.ProxyBillSummary2gameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:41
 * 代理游戏部分退佣汇总
 */
@Service
public class ProxyBillSummary2gameAssemService {
    @Resource
    private ProxyBillSummary2gameService proxyBillSummary2gameService;

    public boolean save(ProxyBillSummary2game game) {
        return proxyBillSummary2gameService.save(game);
    }
}
