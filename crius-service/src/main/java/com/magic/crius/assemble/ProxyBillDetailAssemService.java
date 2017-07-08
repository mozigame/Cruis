package com.magic.crius.assemble;

import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.service.ProxyBillDetailService;
import com.magic.crius.vo.AgentBillReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:33
 * 代理账单汇总
 */
@Service
public class ProxyBillDetailAssemService {

    @Resource
    private ProxyBillDetailService proxyBillDetailService;


    public boolean save(ProxyBillDetail detail) {
        return proxyBillDetailService.save(detail);
    }

    /**
     * 组装代理账单汇总
     */
    public void assemblePBD(AgentBillReq req) {


    }
}
