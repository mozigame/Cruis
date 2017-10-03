package com.magic.crius.service.thrift;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.analysis.utils.JsonUtils;
import com.magic.api.commons.ApiLogger;
import com.magic.commons.gateway.ThriftService;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGHeader;
import com.magic.config.thrift.base.EGReq;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.vo.StmlBillInfoReq;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: joey
 * Date: 2017/6/23
 * Time: 19:35
 */
@Service
public class CriusThirdThriftService {

    private static ThriftService thriftService = ThriftService.getInstance();

    /**
     * 拉取游戏列表
     * @param body
     * @param caller
     * @return
     */
    public EGResp getAllGames(String body, String caller) {
        EGReq egReq = assembleEGReq(CmdType.CONFIG, 0x502004, body);
        return thriftService.call(egReq, caller);
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
            EGResp call = thriftService.call(req, "crius");
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
     * 获取代理参数配置信息
     * @param uid
     * @return
     */
    public EGResp getAgentConfig(long uid) {
        String body = "{\"agentId\":" + uid + "}";
        EGReq req = assembleEGReq(CmdType.CONFIG, 0x500043, body);
        return thriftService.call(req, "crius");

    }

    /**
     * 代理和业主月结账单
     * @param stmlBillInfoReq
     * @return
     */
    public EGResp getOwnerMonthBill(StmlBillInfoReq stmlBillInfoReq) {
        String body = JsonUtils.toJsonStringTrimNull(stmlBillInfoReq);
        ApiLogger.info("代理和业主月结账单任务调度请求报文：" + body);
        return thriftService.call(CmdType.SETTLE, 0x300013, body, "crius");
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
