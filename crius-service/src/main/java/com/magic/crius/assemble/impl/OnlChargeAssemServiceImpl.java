package com.magic.crius.assemble.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.OnlChargeAssemService;
import com.magic.crius.vo.Parent;
import org.springframework.stereotype.Service;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 */
@Service("onlChargeAssembleService")
public class OnlChargeAssemServiceImpl implements OnlChargeAssemService {

    private void json() {
        JSONObject object = new JSONObject();
        JSONObject j1 = new JSONObject();
        object.put("j1", j1);
        JSON.parseObject(object.toJSONString(), Parent.class);

    }
}
