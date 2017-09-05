package com.magic.crius.controller;

import com.magic.crius.scheduled.consumer.MonthJobConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/9/1
 * Time: 14:21
 * 月结账单
 */
@Controller
@RequestMapping("/v1/crius/monthBill")
public class MonthBillController {

    @Resource
    private MonthJobConsumer monthJobConsumer;

    /**
     * 手动纠正月结账单
     * @return
     */
    @RequestMapping(value = "/artificialBill", method = RequestMethod.POST)
    @ResponseBody
    public String rectify(
    ) {
        return monthJobConsumer.artificialBill();
    }
}
