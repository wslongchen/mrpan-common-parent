package com.mrpan.user.dao.impl;

import com.mrpan.user.bean.Ann_User;
import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.user.dao.Ann_UserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
@Component("ann_UserDao")
public class Ann_UserDaoImpl extends BaseDaoImpl<Ann_User> implements Ann_UserDao {
    public List<Ann_User> listUser(String jpq) {
        String strWhereString="from Ann_User";
        if(StringUtils.isNotBlank(jpq.trim())){
            String temp=jpq.substring(0,"where".length());
            if(!"where".toLowerCase().equals(temp.toLowerCase())){
                strWhereString+=" where";
            }
            strWhereString += " " +jpq+" ";
        }
        System.out.println(strWhereString);
        return this.list(strWhereString);
    }
}