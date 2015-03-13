package com.dcw.adaoframework.dao;

/**
 * <p>Title: ucweb</p>
 * <p/>
 * <p>Description: </p>
 * ......
 * <p>Copyright: Copyright (c) 2015</p>
 * <p/>
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/3/12
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 数据库访问操作对象
 * @author Wang
 * @param <T> 实体类
 * @param <PK> 主键类型
 */
public interface IDataBaseDao<T extends Serializable, PK extends Serializable> {

    /**
     * 设置实体类
     * @param entityClass 实体类class
     */
    void setEntityClass(Class<T> entityClass);

    /**
     * 新建一个查询对象
     * @return
     */
    IQuery createQuery();

    /**
     * 获取数据
     * @param id 主键
     * @return
     */
    T get(PK id);

    /**
     * 加载数据
     * @param id 主键
     * @return
     */
    T load(PK id);

    /**
     * 加载全部数据
     * @return
     */
    List<T> loadAll();

    /**
     * 查询数据
     * @param query 查询对象
     * @return
     */
    List<T> find(IQuery query);

    /**
     * 保存数据
     * @param entity 实体类
     * @return
     */
    PK save(T entity);

    /**
     * 更新数据
     * @param entity 实体类
     */
    void update(T entity);

    /**
     * 保存或更新数据
     * @param entity 实体类
     */
    void saveOrUpdate(T entity);

    /**
     * 保存或更新全部数据
     * @param entities 实体类集合
     */
    void saveOrUpdateAll(Collection<T> entities);

    /**
     * 删除数据
     * @param entity 实体类
     */
    void delete(T entity);

    /**
     * 依据主键删除数据
     * @param id 主键
     */
    void deleteByKey(PK id);

    /**
     * 删除全部数据
     * @param entities 实体类集合
     */
    void deleteAll(Collection<T> entities);
}

