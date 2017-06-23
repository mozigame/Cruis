package com.magic.crius.service.thrift;

import com.magic.commons.enginegw.service.ThriftFactory;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGHeader;
import com.magic.config.thrift.base.EGReq;
import com.magic.config.thrift.base.EGResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/23
 * Time: 19:35
 */
@Service
public class CriusThirdThriftService {

    @Resource
    private ThriftFactory thriftFactory;

    /**
     * 会员注册
     * @param body
     * @param caller
     * @return
     */
    public EGResp findGames(String body, String caller) {
        //todo
        EGReq egReq = assembleEGReq(CmdType.GAME, 0x100001, body);
        return thriftFactory.call(egReq, caller);
    }

    /**
     * 组装thrift请求对象
     *
     * @param cmdType
     * @param cmd
     * @param body
     * @return
     */
    private EGReq assembleEGReq(CmdType cmdType, int cmd, String body) {
        EGReq req = new EGReq();
        EGHeader header = new EGHeader();
        header.setType(cmdType);
        header.setCmd(cmd);
        req.setHeader(header);
        req.setBody(body);
        return req;
    }

}
