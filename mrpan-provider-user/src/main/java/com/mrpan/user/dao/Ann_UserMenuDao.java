package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.user.bean.Ann_UserMenu;

import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
public interface Ann_UserMenuDao extends IBaseDao<Ann_UserMenu> {
    public List<Ann_UserMenu> listJpq(String jpq);
}
