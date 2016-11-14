package com.mrpan.user.service;

import com.mrpan.user.bean.Ann_User;

/**
 * Created by mrpan on 2016/11/3.
 */
public interface Ann_UserService {
    /**
     *
     *  登录用户
     * @param name
     * @param pwd
     * @return
     * @throws Exception
     */
    Ann_User findUser(String name, String pwd) throws Exception;

    /**
     *
     *  新增用户
     * @param annUser
     * @return
     * @throws Exception
     */
    Ann_User addUser(Ann_User annUser) throws Exception;
}
