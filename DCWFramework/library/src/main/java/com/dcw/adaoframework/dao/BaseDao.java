package com.dcw.adaoframework.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
public class BaseDao<T extends Serializable, PK extends Serializable> implements IDataBaseDao<T, PK> {

    @Override
    public void setEntityClass(Class<T> entityClass) {

    }

    @Override
    public IQuery createQuery() {
        return null;
    }

    @Override
    public T get(PK id) {
        return null;
    }

    @Override
    public T load(PK id) {
        return null;
    }

    @Override
    public List<T> loadAll() {
        return null;
    }

    @Override
    public List<T> find(IQuery query) {
        return null;
    }

    @Override
    public PK save(T entity) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void saveOrUpdate(T entity) {

    }

    @Override
    public void saveOrUpdateAll(Collection<T> entities) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteByKey(PK id) {

    }

    @Override
    public void deleteAll(Collection<T> entities) {

    }
}
