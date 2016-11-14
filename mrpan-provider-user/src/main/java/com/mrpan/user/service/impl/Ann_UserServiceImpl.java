package com.mrpan.user.service.impl;

import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.dao.Ann_UserDao;
import com.mrpan.user.service.Ann_UserService;
import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.QueryKit;
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
@Component("ann_UserService")
public class Ann_UserServiceImpl implements Ann_UserService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_UserServiceImpl.class);

    @Autowired
    private Ann_UserDao userDao;

    public Ann_User findUser(String name, String pwd) {
        try {
            List<FourObject> where=new ArrayList<FourObject>();
            where.add(new FourObject("userName",name));
            if(StringUtils.isNotBlank(pwd))
                where.add(new FourObject("password",pwd));
            String jpql= QueryKit.createQuerySqlByFour(where);
            List<Ann_User> annUsers =this.userDao.listUser(jpql);
            if(annUsers !=null && annUsers.size()>0){
                return annUsers.get(0);
            }
        } catch (Exception e) {
            logger.error(
                    "failed to loginUser by name[" + name + "] "
                            + e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    public Ann_User addUser(Ann_User annUser){
        Ann_User u=this.userDao.add(annUser);
        return u;
    }

}
