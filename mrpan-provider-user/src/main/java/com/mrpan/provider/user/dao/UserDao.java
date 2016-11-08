package com.mrpan.provider.user.dao;

import com.mrpan.api.user.bean.User;
import com.mrpan.common.core.dao.IBaseDao;

import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface UserDao extends IBaseDao<User> {
    List<User> listUser(String jpq);
}
