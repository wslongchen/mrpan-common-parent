package com.mrpan.user.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Game;

import java.util.List;

/**
 * Created by mrpan on 2017/2/13.
 */
public interface Ann_GameDao extends IBaseDao<Ann_Game> {
    Pager<Ann_Game> findGames(String jpq);
    List<Ann_Game> listJpq(String jpq);
}
