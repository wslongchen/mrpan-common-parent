package com.mrpan.common.core.dao.impl;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.SystemContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mrpan on 2016/11/3.
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
    @PersistenceContext
    public EntityManager em;
    private Class<?> clazz=null;

    public Class<?> getClazz(){
        if(clazz==null){
            clazz=((Class<?>)(((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clazz;
    }

    public T add(T entity) {
        this.em.persist(entity);
        return null;
    }

    public void update(T entity) {

        this.em.merge(entity);
    }

    public void delete(Serializable id) {
        this.em.remove(this.getObj(id));

    }

    public T getObj(Serializable id) {
        return (T) this.em.find(this.getClazz(), id);
    }


    public List<T> list(String jpq, Object[] args) {
        return this.list(jpq, args, null);
    }

    public List<T> list(String jpq, Object arg) {
        return this.list(jpq, new Object[] { arg });
    }

    public List<T> list(String jpq) {
        return this.list(jpq, null);
    }

    private String initSort(String jpq) {
        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();
        if (sort != null && !"".equals(sort.trim())) {
            jpq += " order by " + sort;
            if (!"desc".equalsIgnoreCase(order))
                jpq += " asc";
            else
                jpq += " desc";
        }
        return jpq;
    }

    private void setAliasParameter(Query query, Map<String, Object> alias) {
        if (alias != null) {
            Set<String> keys = alias.keySet();
            for (String key : keys) {
                Object val = alias.get(key);
                if (val instanceof Collection) {
                    // 查询条件是列表
                    // query.setParameterList(key, (Collection)val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    private void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            // int index = 0;
            int index = 1; // JPA index从 1开始
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> list(String jpq, Object[] args, Map<String, Object> alias) {
        jpq = initSort(jpq);
        Query query = this.em.createQuery(jpq);
        setAliasParameter(query, alias);
        setParameter(query, args);
        // 万一有分页要求呢？
        Integer pageNo = SystemContext.getPageNo();
        Integer pageSize = SystemContext.getPageSize();
        if ((pageNo != null && pageSize != null) && (pageNo != 0 && pageSize != 0)) {
            query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
        }
        return query.getResultList();
    }

    public List<T> listByAlias(String jpq, Map<String, Object> alias) {
        return this.list(jpq, null, alias);
    }

    public Pager<T> find(String jpq, Object[] args) {
        return this.find(jpq, args, null);
    }

    public Pager<T> find(String jpq, Object arg) {
        return this.find(jpq, new Object[] { arg });
    }

    public Pager<T> find(String jpq) {
        return this.find(jpq, null);
    }

    @SuppressWarnings("rawtypes")
    private void setPagers(Query query, Pager pages) {
        // 每页显示多少条数据
        Integer pageSize = SystemContext.getPageSize();
        // 当前页
        Integer pageNo = SystemContext.getPageNo();
        if (pageNo == null || pageNo < 0)
            pageNo = 1;
        if (pageSize == null || pageSize < 0)
            pageSize = 15;
        pages.setPageNo(pageNo);
        pages.setPageSize(pageSize);
        query.setFirstResult(pages.getFirstResult()).setMaxResults(pageSize);
    }

    private String getCountByJpq(String jpq, boolean isJpq) {
        String e = jpq.substring(jpq.indexOf("from"));
        String c = "select count(*) " + e;
        if (isJpq)
            c = c.replaceAll("fetch", "");
        return c;
    }

    @SuppressWarnings("unchecked")
    public Pager<T> find(String jpq, Object[] args, Map<String, Object> alias) {
        jpq = initSort(jpq);
        String cq = getCountByJpq(jpq, false);
        Query cquery = this.em.createQuery(cq);
        Query query = this.em.createQuery(jpq);
        // 设置别名参数
        setAliasParameter(query, alias);
        setAliasParameter(cquery, alias);
        // 设置参数
        setParameter(query, args);
        setParameter(cquery, args);
        Pager<T> pages = new Pager<T>();
        setPagers(query, pages);
        List<T> datas = query.getResultList();
        pages.setDatas(datas);
        long totalCount = (Long) cquery.getSingleResult();
        pages.setTotalCount(totalCount);
        return pages;
    }

    public Pager<T> findByAlias(String jpq, Map<String, Object> alias) {
        return this.find(jpq, null, alias);
    }

    public Object queryObject(String jpq, Object[] args) {
        return this.queryObject(jpq, args, null);
    }

    public Object queryObject(String jpq, Object arg) {
        return this.queryObject(jpq, new Object[] { arg });
    }

    public Object queryObject(String jpq) {
        return this.queryObject(jpq, null);
    }

    public int updateByjpq(String jpq, Object[] args) {
        Query query = this.em.createQuery(jpq);
        setParameter(query, args);
        return query.executeUpdate();
    }

    public int updateByjpq(String jpq, Object arg) {
        return this.updateByjpq(jpq, new Object[] { arg });
    }

    public int updateByjpq(String jpq) {
        return this.updateByjpq(jpq, null);
    }

    public int updateBySql(String jpqSql, Object[] args) {
        Query query = this.em.createNativeQuery(jpqSql);
        setParameter(query, args);
        return query.executeUpdate();
    }

    public int updateBySql(String sql, Object obj) {
        return this.updateByjpq(sql, new Object[] { obj });
    }

    public int updateBySql(String sql) {
        return this.updateByjpq(sql, null);
    }

    public void deleteByjpq(String jpq, Object[] args) {
        Query query = this.em.createQuery(jpq);
        setParameter(query, args);
        query.executeUpdate();
    }

    public void deleteByjpq(String jpq, Object arg) {
        this.deleteByjpq(jpq, new Object[] { arg });
    }

    public void deleteByjpq(String jpq) {
        this.deleteByjpq(jpq, null);
    }

    public <N extends Object> List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, new Object[] { arg }, clz, hasEntity);
    }

    public <N extends Object> List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, clz, hasEntity);
    }

    @SuppressWarnings("unchecked")
    public <N extends Object> List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        Query sq = null;
        if (null == clz) {
            sq = this.em.createNativeQuery(sql);
        } else {
            sq = this.em.createNativeQuery(sql, clz);
        }
        setAliasParameter(sq, alias);
        setParameter(sq, args);

        return sq.getResultList();
    }

    public <N extends Object> List<N> listByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return this.listBySql(sql, null, alias, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, new Object[] { arg }, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, clz, hasEntity);
    }

    @SuppressWarnings("unchecked")
    public <N extends Object> Pager<N> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        sql = initSort(sql);
        String cq = getCountByJpq(sql, false);
        Query sq = this.em.createNativeQuery(sql);
        Query cquery = this.em.createNativeQuery(cq);
        setAliasParameter(sq, alias);
        setAliasParameter(cquery, alias);
        setParameter(sq, args);
        setParameter(cquery, args);
        Pager<N> pages = new Pager<N>();
        setPagers(sq, pages);

        List<N> datas = sq.getResultList();
        pages.setDatas(datas);
        long totalCount = ((BigInteger) cquery.getSingleResult()).longValue();
        pages.setTotalCount(totalCount);
        return pages;
    }

    public <N extends Object> Pager<N> findByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
        return this.findBySql(sql, null, alias, clz, hasEntity);
    }

    public Object queryObject(String jpq, Object[] args, Map<String, Object> alias) {
        Query query = this.em.createQuery(jpq);
        setAliasParameter(query, alias);
        setParameter(query, args);
        List results = query.getResultList();
        // http://stackoverflow.com/questions/25616374/javax-persistence-noresultexception-no-entity-found-for-query-jpql-query
        // return query.getSingleResult();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
		/*
		 * Object result = null; try { result = query.getSingleResult(); } catch
		 * (NoResultException e) { // TODO } return result;
		 */
    }

    public Object queryObjectByAlias(String jpq, Map<String, Object> alias) {
        return this.queryObject(jpq, null, alias);
    }

    public void update(String tablename, String sets, String where) {
        // TODO Auto-generated method stub

        String strWhere = "";
        where = where.trim();
        if (!"".equalsIgnoreCase(where)) {
            String temp = where.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + where + " ";
        }
        String strSet = "";
        sets = sets.trim();
        if (!"".equalsIgnoreCase(sets)) {
            String temp = sets.substring(0, "set".length());
            if (!"set".toLowerCase().equals(temp.toLowerCase())) {
                strSet += "set ";
            }
            strSet += " " + sets + " ";
        }
        String sqlString = "update  " + tablename + " " + strSet + " " + strWhere;
        Query query = this.em.createNativeQuery(sqlString);
        query.executeUpdate();

    }


}

