package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.user.bean.Ann_UserMenu;
import com.mrpan.user.dao.Ann_UserMenuDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
@Component("ann_UserMenuDao")
public class Ann_UserMenuDaoImpl extends BaseDaoImpl<Ann_UserMenu> implements Ann_UserMenuDao {

    private static final Logger logger = LoggerFactory.getLogger(Ann_UserMenuDaoImpl.class);

    public List<Ann_UserMenu> listJpq(String jpq) {
        String strWhere = " from Ann_UserMenu ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for list Sema_UserMenu string is :" + strWhere);
        return this.list(strWhere);
    }
}
