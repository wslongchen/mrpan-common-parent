package com.mrpan.user.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.common.core.utils.SystemContext;
import com.mrpan.user.bean.Ann_Bike;
import com.mrpan.user.bean.Ann_Game;
import com.mrpan.user.dao.Ann_BikeDao;
import com.mrpan.user.dao.Ann_GameDao;
import com.mrpan.user.service.Ann_BikeService;
import com.mrpan.user.service.Ann_GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/2/14.
 */
@Component("ann_BikeService")
public class Ann_BikeServiceImpl implements Ann_BikeService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_BikeServiceImpl.class);

    @Autowired
    private Ann_BikeDao ann_BikeDao;

    public List<Ann_Bike> listBikes(List<FourObject> mapWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(mapWhere);
            List<Ann_Bike> list=this.ann_BikeDao.listJpq(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Pager<Ann_Bike> findBikes(Integer pageNo, Integer pageSize, List<FourObject> mapWhere) {
        try {
            SystemContext.setPageNo(pageNo);
            SystemContext.setPageSize(pageSize);
            SystemContext.setOrder("createDate Desc");
            String jpq = QueryKit.createQuerySqlByFour(mapWhere);
            return this.ann_BikeDao.findBikes(jpq);
        } catch (Exception e) {
            logger.error("failed to pager findBikes with Criteria[" + mapWhere + "] " + e.getMessage(), e);
        } finally {
            SystemContext.removePageNo();
            SystemContext.removePageSize();
            SystemContext.removeOrder();
        }
        return null;
    }

    public Ann_Bike addBike(Ann_Bike bike) {
        return this.ann_BikeDao.add(bike);
    }

    public void updateBike(Ann_Bike b) {
        this.ann_BikeDao.update(b);
    }
}
