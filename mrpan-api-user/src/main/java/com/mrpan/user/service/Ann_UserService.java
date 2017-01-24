package com.mrpan.user.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.bean.Ann_Vpn;

import java.util.List;

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

    /**
     * 获取用户列表
     * @param mapWhere
     * @return
     */
    List<Ann_User> listUsers(List<FourObject> mapWhere);

    /**
     * 更新用户资料
     * @param user
     */
    void updateUser(Ann_User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    void updateEmailByOpenId(String email,String openId);
}
