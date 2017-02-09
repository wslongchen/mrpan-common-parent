package com.mrpan.user.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.AnnMenuTree;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Menu;

import java.util.List;

/**
 * Created by mrpan on 2017/2/9.
 */
public interface Ann_CashService {
    public List<Ann_Cash>  listCashs(List<FourObject> mapWhere);

    public Pager<Ann_Cash> findCashs(Integer pageNo,Integer pageSize,List<FourObject> mapWhere);

    Ann_Cash addCash(Ann_Cash cash);
}
