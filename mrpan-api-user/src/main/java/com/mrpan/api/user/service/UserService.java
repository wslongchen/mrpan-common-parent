package com.mrpan.api.user.service;

import com.mrpan.api.user.bean.User;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface UserService {
    /**
     *
     *  登录用户
     * @param name
     * @param pwd
     * @return
     * @throws Exception
     */
    User findUser(String name, String pwd) throws Exception;

    /**
     *
     *  新增用户
     * @param user
     * @return
     * @throws Exception
     */
    User addUser(User user) throws Exception;
}
