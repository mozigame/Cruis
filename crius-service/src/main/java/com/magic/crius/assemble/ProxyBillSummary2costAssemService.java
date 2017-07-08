package com.magic.crius.assemble;

import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.service.ProxyBillSummary2costService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:36
 * 代理成本部分退佣汇总
 */
@Service
public class ProxyBillSummary2costAssemService {


    @Resource
    private ProxyBillSummary2costService proxyBillSummary2costService;

    public boolean save(ProxyBillSummary2cost cost) {
        return proxyBillSummary2costService.save(cost);
    }
}
