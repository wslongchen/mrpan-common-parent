package com.mrpan.user.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Bike;
import com.mrpan.user.bean.Ann_Game;

import java.util.List;

/**
 * Created by mrpan on 2017/2/14.
 */
public interface Ann_BikeService {
    public List<Ann_Bike> listBikes(List<FourObject> mapWhere);

    public Pager<Ann_Bike> findBikes(Integer pageNo, Integer pageSize, List<FourObject> mapWhere);

    Ann_Bike addBike(Ann_Bike bike);

    void updateBike(Ann_Bike b);
}
