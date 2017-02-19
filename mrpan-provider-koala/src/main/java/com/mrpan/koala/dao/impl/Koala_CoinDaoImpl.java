package com.mrpan.koala.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.dao.Koala_CoinDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
@Component("Koala_CoinDao")
public class Koala_CoinDaoImpl extends BaseDaoImpl<Koala_Coin> implements Koala_CoinDao {
    private static final Logger logger = LoggerFactory.getLogger(Koala_CoinDaoImpl.class);

    public Pager<Koala_Coin> findCoins(String jpq) {
        String strWhere = " from Koala_Coin ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for find findCoins string is :" + strWhere);
        return this.find(strWhere);
    }

    public List<Koala_Coin> listJpq(String jpq) {
        String strWhere = " from Koala_Coin ";
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
