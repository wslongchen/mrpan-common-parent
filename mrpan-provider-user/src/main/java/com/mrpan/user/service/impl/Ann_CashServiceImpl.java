package com.mrpan.user.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.common.core.utils.SystemContext;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Vpn;
import com.mrpan.user.dao.Ann_CashDao;
import com.mrpan.user.service.Ann_CashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/2/9.
 */
@Component("ann_CashService")
public class Ann_CashServiceImpl implements Ann_CashService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_VpnServiceImpl.class);

    @Autowired
    private Ann_CashDao ann_CashDao;

    public List<Ann_Cash> listCashs( List<FourObject> mapWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(mapWhere);
            List<Ann_Cash> list=this.ann_CashDao.listJpq(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Pager<Ann_Cash> findCashs(Integer pageNo, Integer pageSize,List<FourObject> mapWhere) {
        try {
            SystemContext.setPageNo(pageNo);
            SystemContext.setPageSize(pageSize);
            SystemContext.setOrder("createDate Desc");
            String jpq = QueryKit.createQuerySqlByFour(mapWhere);
            return this.ann_CashDao.findCash(jpq);
        } catch (Exception e) {
            logger.error("failed to pager findNewMerchants with Criteria[" + mapWhere + "] " + e.getMessage(), e);
        } finally {
            SystemContext.removePageNo();
            SystemContext.removePageSize();
            SystemContext.removeOrder();
        }
        return null;
    }

    public Ann_Cash addCash(Ann_Cash cash) {
        return this.ann_CashDao.add(cash);
    }
}
