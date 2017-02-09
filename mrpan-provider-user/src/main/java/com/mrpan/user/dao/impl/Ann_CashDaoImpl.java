package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.dao.Ann_CashDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2017/2/9.
 */
@Component("ann_CashDao")
public class Ann_CashDaoImpl extends BaseDaoImpl<Ann_Cash> implements Ann_CashDao {
    private static final Logger logger = LoggerFactory.getLogger(Ann_CashDaoImpl.class);

    public Pager<Ann_Cash> findCash(String jpq) {
        String strWhere = " from Ann_Cash ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for find findCash string is :" + strWhere);
        return this.find(strWhere);
    }

    public List<Ann_Cash> listJpq(String jpq) {
        String strWhere = " from Ann_Cash ";
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
