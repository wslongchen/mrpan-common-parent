package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.user.bean.Ann_UserMenu;
import com.mrpan.user.bean.Ann_Wechat;
import com.mrpan.user.dao.Ann_UserMenuDao;
import com.mrpan.user.dao.Ann_WechatDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mrpan on 2017/1/23.
 */
@Component("ann_WechatDao")
public class Ann_WechatDaoImpl extends BaseDaoImpl<Ann_Wechat> implements Ann_WechatDao {

    private static final Logger logger = LoggerFactory.getLogger(Ann_WechatDaoImpl.class);

    public List<Ann_Wechat> listJpq(String jpq) {
        String strWhereString="from Ann_Wechat";
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

    public Ann_Wechat getWechat(Integer id) {
        return this.getObj(id);
    }

    public void updateAccessToken(String token) {
        try{
            Ann_Wechat wechat=this.getObj(1);
            wechat.setToken(token);
            this.update(wechat);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
