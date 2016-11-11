package com.mrpan.user.dao;

import com.mrpan.user.bean.User;
import com.mrpan.common.core.dao.IBaseDao;

import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface UserDao extends IBaseDao<User> {
    List<User> listUser(String jpq);
}
