package com.mrpan.provider.user.service.impl;

import com.mrpan.api.user.bean.User;
import com.mrpan.api.user.service.UserService;
import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.provider.user.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
@Component("userService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public User findUser(String name, String pwd) {
        try {
            List<FourObject> where=new ArrayList<FourObject>();
            where.add(new FourObject("name",name));
            if(StringUtils.isNotBlank(pwd))
                where.add(new FourObject("password",pwd));
            String jpql= QueryKit.createQuerySqlByFour(where);
            List<User> users=this.userDao.listUser(jpql);
            if(users !=null && users.size()>0){
                return users.get(0);
            }
        } catch (Exception e) {
            logger.error(
                    "failed to loginUser by name[" + name + "] "
                            + e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    public User addUser(User user){
        User u=this.userDao.add(user);
        return u;
    }

}
