package com.mrpan.koala.dao;

import com.mrpan.common.core.dao.IBaseDao;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.bean.Koala_Step;

import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
public interface Koala_StepDao extends IBaseDao<Koala_Step> {
    Pager<Koala_Step> findSteps(String jpq);
    List<Koala_Step> listJpq(String jpq);
}
