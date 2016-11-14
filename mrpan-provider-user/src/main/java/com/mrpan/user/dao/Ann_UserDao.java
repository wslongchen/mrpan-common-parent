package com.mrpan.user.dao;

import com.mrpan.user.bean.Ann_User;
import com.mrpan.common.core.dao.IBaseDao;

import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface Ann_UserDao extends IBaseDao<Ann_User> {
        List<Ann_User> listUser(String jpq);
}
