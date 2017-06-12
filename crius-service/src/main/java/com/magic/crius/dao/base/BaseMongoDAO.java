package com.magic.crius.dao.base;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;


public interface BaseMongoDAO<T> {

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    public List<T> find(Query query);

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    List<T> find(Query query,String collectionName);

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    public T update(Query query, Update update);

    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     * @return
     */
    public T save(T entity);

    /**
     * 批量插入数据
     * @param objects
     * @return
     */
    <X> boolean save(Collection<X> objects, String collectionName);

    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     * @return
     */
    public T save(T entity, String collectionName);

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName 集合名
     * @return
     */
    public T findById(String id, String collectionName);

    /**
     * 求数据总和
     *
     * @param query
     * @return
     */
    public long count(Query query);


    /**
     * 查询操作成功的ID列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<Long> getSucIds(Long startTime, Long endTime, String collectionName);

    /**
     * 获取固定时间内未处理的数据
     * @param startTime
     * @param endTime
     * @param reqIds
     * @return
     */
    List<T> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, String collectionName);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<T> getSaveFailed(Long startTime, Long endTime, String collectionName);


}
