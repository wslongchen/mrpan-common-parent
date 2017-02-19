package com.mrpan.koala.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.common.core.utils.SystemContext;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.dao.Koala_CoinDao;
import com.mrpan.koala.dao.Koala_StepDao;
import com.mrpan.koala.service.Koala_CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
@Component("koala_CoinService")
public class Koala_CoinServiceImpl implements Koala_CoinService {
    private static final Logger logger = LoggerFactory
            .getLogger(Koala_CoinServiceImpl.class);

    @Autowired
    private Koala_CoinDao koala_CoinDao;

    public List<Koala_Coin> listCoins(List<FourObject> mapWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(mapWhere);
            List<Koala_Coin> list=this.koala_CoinDao.listJpq(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Pager<Koala_Coin> findCoins(Integer pageNo, Integer pageSize, List<FourObject> mapWhere) {
        try {
            SystemContext.setPageNo(pageNo);
            SystemContext.setPageSize(pageSize);
            SystemContext.setOrder("createDate Desc");
            String jpq = QueryKit.createQuerySqlByFour(mapWhere);
            return this.koala_CoinDao.findCoins(jpq);
        } catch (Exception e) {
            logger.error("failed to pager findCoins with Criteria[" + mapWhere + "] " + e.getMessage(), e);
        } finally {
            SystemContext.removePageNo();
            SystemContext.removePageSize();
            SystemContext.removeOrder();
        }
        return null;
    }

    public Koala_Coin addCoin(Koala_Coin coin) {
        return koala_CoinDao.add(coin);
    }

    public void updateCoin(Koala_Coin coin) {
        koala_CoinDao.update(coin);
    }
}
