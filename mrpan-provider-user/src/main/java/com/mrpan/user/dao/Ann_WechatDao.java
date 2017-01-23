package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.user.bean.Ann_UserMenu;
import com.mrpan.user.bean.Ann_Wechat;

import java.util.List;

/**
 * Created by mrpan on 2017/1/23.
 */
public interface Ann_WechatDao extends IBaseDao<Ann_Wechat> {
    public List<Ann_Wechat> listJpq(String jpq);
    public Ann_Wechat getWechat(Integer id);
    public void updateAccessToken(String token);
}

