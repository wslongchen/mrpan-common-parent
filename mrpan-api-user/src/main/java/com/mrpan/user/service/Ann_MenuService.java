package com.mrpan.user.service;

import com.mrpan.user.bean.AnnMenuTree;
import com.mrpan.user.bean.Ann_Menu;

import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
public interface Ann_MenuService {
    public List<Ann_Menu> lazyLoadUserMenu(In parentId);

    public AnnMenuTree getMenuTree(int userId);
}

