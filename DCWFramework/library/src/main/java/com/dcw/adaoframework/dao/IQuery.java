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

import java.util.List;

/**
 * 查询操作对象
 * @author Wang
 */
public interface IQuery {

    /**
     * 获取对象查询语句
     * @return
     */
    String getQueryString();

    /**
     * 获取sql查询语句
     * @return
     */
    String getSQLString();

    /**
     * 获取查询参数集合
     * @return
     */
    Object[] getParamValues();

    /**
     * 获取查询数据的起始位号，用于分页
     * @return
     */
    int getFirstResult();

    /**
     * 获取查询数据的最大长度，用于分页
     * @return
     */
    int getMaxResults();

    /**
     * 设置表的实体类集合
     * @param entityClasses 实体类class集合
     */
    @SuppressWarnings("rawtypes")
    void setEntityClass(Class[] entityClasses);

    /**
     * 设置对象查询语句
     * @param queryStr 对象查询语句字符串
     */
    void setQueryString(String queryStr);

    /**
     * 设置sql查询语句
     * @param sql sql查询语句字符串
     */
    void setSQLString(String sql);

    /**
     * 设置查询参数集合
     * @param values 参数值集合
     */
    void setParamValues(Object[] values);

    /**
     * 设置查询数据的起始值，用于分页
     * @param value 起始值
     */
    void setFirstResult(int value);

    /**
     * 设置查询数据的最大长度，用于分页
     * @param value 最大值
     */
    void setMaxResults(int value);

    /**
     * 执行查询
     * @return
     */
    @SuppressWarnings("rawtypes")
    List execute();
}

