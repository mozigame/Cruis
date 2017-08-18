package com.magic.crius.assemble;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.assemble.UserPreferentialDetailAssemService;
import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.service.UserPreferentialDetailService;
import com.magic.crius.storage.mongo.DiscountReqMongoService;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:37
 */
@Service
public class UserPreferentialDetailAssemService {

    @Resource
    private UserPreferentialDetailService userPreferentialDetailService;

    @Resource
    private DiscountReqMongoService discountReqMongoService;

    public void batchSave(List<UserPreferentialDetail> summaries) {

        //todo 错误处理
        if (summaries.size() > 0) {
            boolean saveResult = userPreferentialDetailService.batchInsert(summaries);
        }
    }


    /**
     * @param req
     * @return
     */
    public UserPreferentialDetail assembleUserPreferentialDetail(DiscountReq req) {
        if (1==1){
            throw  new RuntimeException("ssss");
        }
        UserPreferentialDetail detail = new UserPreferentialDetail();

        detail.setBillId(req.getBillId());
        detail.setReqId(req.getReqId());

        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setPreferentialMoneyCount(req.getOfferAmount());
        //todo 优惠次数
        detail.setPreferentialNum(1);
        detail.setPreferentialType(req.getOfferTypeId());
        detail.setPreferentialTypeName(req.getOfferTypeName());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        detail.setRemark(req.getRemark());//优惠备注
        return detail;
    }

    /**
     * 修复UserPreferential表
     * 从mongo抓取数据,写入db
     *
     * @param idList
     * @return
     */
    public String repairUserPreferential(String idList) {
        if (StringUtils.isBlank(idList)) {
            throw new RuntimeException("repairUserPreferential::idList error");
        }
        Map<String, Object> result = new HashMap<>();
        String[] ids = idList.split(",");
        for (String idString : ids) {
            Long id = Long.parseLong(idString);
            DiscountReq discountReq = discountReqMongoService.getByReqId(id);
            if (discountReq == null) {
                result.put(idString, "null");
            } else {
                UserPreferentialDetail detail = assembleUserPreferentialDetail(discountReq);
                UserPreferentialDetail detailInDB = userPreferentialDetailService.getByBillId(detail.getBillId());
                if (detailInDB==null){
                    int repairCount = userPreferentialDetailService.repairDetail(detail);
                    result.put(idString, repairCount);
                }else {
                    result.put(idString, "exist");
                }
            }
        }

        result.put("oper", true);
        return JSONObject.toJSONString(result);
    }

    public String repairUserPreferentialByPage(int page, int count) {
        long now = System.currentTimeMillis();
        if (page<1){
            throw new RuntimeException("repairUserPreferential::page error");
        }
        if (count>1000){
            throw new RuntimeException("repairUserPreferential::count error");
        }
        Map<String, Object> result = new HashMap<>();
        List<DiscountReq> discountReqList = discountReqMongoService.getByPage(page,count);
        for (int index =0; index<discountReqList.size(); index++){
            DiscountReq discountReq = discountReqList.get(index);
            if (discountReq == null) {
                result.put(String.valueOf(index), "null");
            } else {
                UserPreferentialDetail detail = assembleUserPreferentialDetail(discountReq);
                UserPreferentialDetail detailInDB = userPreferentialDetailService.getByBillId(detail.getBillId());
                if (detailInDB==null){
                    int repairCount = userPreferentialDetailService.repairDetail(detail);
                    result.put(String.valueOf(discountReq.getReqId()), repairCount);
                }else {
                    result.put(String.valueOf(discountReq.getReqId()), "exist");
                }
            }
        }

        result.put("oper", true);
        long cost = System.currentTimeMillis() - now;
        result.put("cost",cost);
        ApiLogger.info("repairUserPreferentialByPage::cost = " + cost);
        return JSONObject.toJSONString(result);
    }

}
