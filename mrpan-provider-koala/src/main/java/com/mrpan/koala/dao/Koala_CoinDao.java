package com.mrpan.koala.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.koala.bean.Koala_Coin;

import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
public interface Koala_CoinDao extends IBaseDao<Koala_Coin> {
    Pager<Koala_Coin> findCoins(String jpq);
    List<Koala_Coin> listJpq(String jpq);
}
