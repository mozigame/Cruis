package com.magic.crius.service.thrift;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.commons.enginegw.service.ThriftFactory;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGHeader;
import com.magic.config.thrift.base.EGReq;
import com.magic.config.thrift.base.EGResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

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
     * 拉取游戏列表
     * @param body
     * @param caller
     * @return
     */
    public EGResp getAllGames(String body, String caller) {
        EGReq egReq = assembleEGReq(CmdType.GAME, 0x600004, body);
        return thriftFactory.call(egReq, caller);
    }

    /**
     * 获取会员详情
     *
     * @param userId
     * @return
     */
    public long getMemberLevel(Long userId){
        String body = "{\"userId\":" + userId + "}";
        EGReq req = assembleEGReq(CmdType.CONFIG, 0x500082, body);
        try {
            EGResp call = thriftFactory.call(req, "caller");
            if (Optional.ofNullable(call).filter(code -> call.getCode() == 0).isPresent()){
                JSONObject object = JSONObject.parseObject(call.getData());
                if (Optional.ofNullable(object).filter(level -> object.getLong("levelId") != null).isPresent()) {
                    return object.getLong("levelId");
                }
            }
        }catch (Exception e){
            ApiLogger.error(String.format("get member level error. req: %s", JSON.toJSONString(req)), e);
        }
        return 0L;
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
