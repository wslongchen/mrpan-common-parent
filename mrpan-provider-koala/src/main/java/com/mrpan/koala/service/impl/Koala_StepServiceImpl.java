package com.mrpan.koala.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.common.core.utils.SystemContext;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.bean.Koala_Step;
import com.mrpan.koala.dao.Koala_StepDao;
import com.mrpan.koala.service.Koala_CoinService;
import com.mrpan.koala.service.Koala_StepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
@Component("koala_StepService")
public class Koala_StepServiceImpl implements Koala_StepService {
    private static final Logger logger = LoggerFactory
            .getLogger(Koala_StepServiceImpl.class);

    @Autowired
    private Koala_StepDao koala_StepDao;

    public List<Koala_Step> listSteps(List<FourObject> mapWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(mapWhere);
            List<Koala_Step> list=this.koala_StepDao.listJpq(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Pager<Koala_Step> findSteps(Integer pageNo, Integer pageSize, List<FourObject> mapWhere) {
        try {
            SystemContext.setPageNo(pageNo);
            SystemContext.setPageSize(pageSize);
            SystemContext.setOrder("step Desc");
            String jpq = QueryKit.createQuerySqlByFour(mapWhere);
            return this.koala_StepDao.findSteps(jpq);
        } catch (Exception e) {
            logger.error("failed to pager findSteps with Criteria[" + mapWhere + "] " + e.getMessage(), e);
        } finally {
            SystemContext.removePageNo();
            SystemContext.removePageSize();
            SystemContext.removeOrder();
        }
        return null;
    }

    public Koala_Step addStep(Koala_Step step) {
        return koala_StepDao.add(step);
    }

    public void updateStep(Koala_Step step) {
        koala_StepDao.update(step);
    }
}
