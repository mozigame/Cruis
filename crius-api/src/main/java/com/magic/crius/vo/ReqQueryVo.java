package com.magic.crius.vo;

import java.util.List;

/**
 * User: joey
 * Date: 2017/9/9
 * Time: 20:18
 */
public class ReqQueryVo {

    private Long startTime;
    private Long endTime;
    private String hhDate;
    private List<Long> reqIds;
    private Integer pdate;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getHhDate() {
        return hhDate;
    }

    public void setHhDate(String hhDate) {
        this.hhDate = hhDate;
    }

    public List<Long> getReqIds() {
        return reqIds;
    }

    public void setReqIds(List<Long> reqIds) {
        this.reqIds = reqIds;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}
