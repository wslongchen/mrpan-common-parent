package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Bike;
import com.mrpan.user.bean.Ann_Game;
import com.mrpan.user.dao.Ann_BikeDao;
import com.mrpan.user.dao.Ann_GameDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2017/2/14.
 */
@Component("ann_BikeDao")
public class Ann_BikeDaoImpl extends BaseDaoImpl<Ann_Bike> implements Ann_BikeDao {
    private static final Logger logger = LoggerFactory.getLogger(Ann_BikeDaoImpl.class);
    public Pager<Ann_Bike> findBikes(String jpq) {
        String strWhere = " from Ann_Bike ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for find findBikes string is :" + strWhere);
        return this.find(strWhere);
    }

    public List<Ann_Bike> listJpq(String jpq) {
        String strWhere = " from Ann_Bike ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for list listJpq string is :" + strWhere);
        return this.list(strWhere);
    }
}
