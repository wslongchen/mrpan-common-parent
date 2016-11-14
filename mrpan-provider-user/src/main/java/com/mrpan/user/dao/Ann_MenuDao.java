package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.user.bean.Ann_Menu;

import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
public interface Ann_MenuDao extends IBaseDao<Ann_Menu> {
    List<Ann_Menu> findMenu(int parentId);

    List<Ann_Menu> listJpq(String jpq);
}
