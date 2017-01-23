package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.bean.Ann_Vpn;
import com.mrpan.user.bean.Ann_Wechat;

import java.util.List;

/**
 * Created by mrpan on 2017/1/23.
 */
public interface Ann_VpnDao  extends IBaseDao<Ann_Vpn> {
    List<Ann_Vpn> listVpnInfos(String jpq);
}
