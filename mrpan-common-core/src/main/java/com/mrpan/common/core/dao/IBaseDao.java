package com.mrpan.common.core.dao;

import java.io.Serializable;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface IBaseDao<T> {
    /**
     * 添加对象
     *
     * @param entity
     * @return
     */
    public T add(T entity);

    /**
     * 更新对象
     *
     * @param entity
     */
    public void update(T entity);

    /**
     * 根据id删除对象
     *
     * @param id
     */
    public void delete(Serializable id);

    /**
     * 根据id加载对象
     *
     * @param id
     * @return
     */
    public T getObj(Serializable id);

    /**
     * @param tablename
     * @param sets
     * @param where
     */
    public void update(String tablename, String sets, String where);
}

