package com.magic.crius.po;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 11:32
 * 修复mongo数据的锁
 */
public class RepairLock {

    private String collectionName;  //表名称
    private Integer time;   //时间 yyyyMMddHH
    private Integer value;  //默认1，表示锁定
    private Long produceTime;    //创建时间

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }
}
