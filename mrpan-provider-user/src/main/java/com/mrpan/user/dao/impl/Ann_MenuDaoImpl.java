package com.mrpan.user.dao.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.user.bean.Ann_Menu;
import com.mrpan.user.dao.Ann_MenuDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrpan on 2016/11/14.
 */
@Component("ann_MenuDao")
public class Ann_MenuDaoImpl extends BaseDaoImpl<Ann_Menu> implements Ann_MenuDao {
    private static final Logger logger = LoggerFactory.getLogger(Ann_MenuDaoImpl.class);

    public List<Ann_Menu> findMenu(int parentId) {
        List<Ann_Menu> list = new ArrayList<Ann_Menu>();
        String jpq = "from  Ann_Menu  where visible=1 and  parentId=? order by menuSort";
        list = this.list(jpq, new Integer[] { parentId });
        return list;
    }

    public List<Ann_Menu> listJpq(String jpq) {
        String strWhere = " from Ann_Menu ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for list Ann_Menu string is :" + strWhere);
        return this.list(strWhere);
    }
}
