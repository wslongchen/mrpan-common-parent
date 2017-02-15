package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Bike;
import com.mrpan.user.bean.Ann_Game;

import java.util.List;

/**
 * Created by mrpan on 2017/2/14.
 */
public interface Ann_BikeDao extends IBaseDao<Ann_Bike> {
    Pager<Ann_Bike> findBikes(String jpq);
    List<Ann_Bike> listJpq(String jpq);
}
