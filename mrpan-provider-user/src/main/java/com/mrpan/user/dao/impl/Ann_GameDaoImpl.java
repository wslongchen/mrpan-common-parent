package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Game;
import com.mrpan.user.dao.Ann_CashDao;
import com.mrpan.user.dao.Ann_GameDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2017/2/13.
 */
@Component("ann_GameDao")
public class Ann_GameDaoImpl extends BaseDaoImpl<Ann_Game> implements Ann_GameDao {

    private static final Logger logger = LoggerFactory.getLogger(Ann_GameDaoImpl.class);
    public Pager<Ann_Game> findGames(String jpq) {
        String strWhere = " from Ann_Game ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for find findGames string is :" + strWhere);
        return this.find(strWhere);
    }

    public List<Ann_Game> listJpq(String jpq) {
        String strWhere = " from Ann_Game ";
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
