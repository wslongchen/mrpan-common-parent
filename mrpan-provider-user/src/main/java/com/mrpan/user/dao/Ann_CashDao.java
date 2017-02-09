package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Cash;

import java.util.List;

/**
 * Created by mrpan on 2017/2/9.
 */
public interface Ann_CashDao extends IBaseDao<Ann_Cash> {
    Pager<Ann_Cash> findCash(String jpq);
    List<Ann_Cash> listJpq(String jpq);
}
