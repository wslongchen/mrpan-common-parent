package com.mrpan.user.dao.impl;

import com.mrpan.common.core.dao.impl.BaseDaoImpl;
import com.mrpan.user.bean.Ann_Vpn;
import com.mrpan.user.dao.Ann_VpnDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mrpan on 2017/1/23.
 */
@Component("ann_VpnDao")
public class Ann_VpnDaoImpl extends BaseDaoImpl<Ann_Vpn> implements Ann_VpnDao {

    private static final Logger logger = LoggerFactory.getLogger(Ann_VpnDaoImpl.class);

    public List<Ann_Vpn> listVpnInfos(String jpq) {
        String strWhere = " from Ann_Vpn ";
        jpq = jpq.trim();
        if (StringUtils.isNotBlank(jpq)) {
            String temp = jpq.substring(0, "where".length());
            if (!"where".toLowerCase().equals(temp.toLowerCase())) {
                strWhere += "where ";
            }
            strWhere += " " + jpq + " ";
        }
        logger.info("Query for list listVpnInfos string is :" + strWhere);
        return this.list(strWhere);
    }
}
