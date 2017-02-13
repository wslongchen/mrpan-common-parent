package com.mrpan.user.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.common.core.utils.SystemContext;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Game;
import com.mrpan.user.dao.Ann_GameDao;
import com.mrpan.user.service.Ann_GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/2/13.
 */
@Component("ann_GameService")
public class Ann_GameServiceImpl implements Ann_GameService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_GameServiceImpl.class);

    @Autowired
    private Ann_GameDao ann_GameDao;

    public List<Ann_Game> listGames(List<FourObject> mapWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(mapWhere);
            List<Ann_Game> list=this.ann_GameDao.listJpq(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Pager<Ann_Game> findGames(Integer pageNo, Integer pageSize, List<FourObject> mapWhere) {
        try {
            SystemContext.setPageNo(pageNo);
            SystemContext.setPageSize(pageSize);
            SystemContext.setOrder("createDate Desc");
            String jpq = QueryKit.createQuerySqlByFour(mapWhere);
            return this.ann_GameDao.findGames(jpq);
        } catch (Exception e) {
            logger.error("failed to pager findGames with Criteria[" + mapWhere + "] " + e.getMessage(), e);
        } finally {
            SystemContext.removePageNo();
            SystemContext.removePageSize();
            SystemContext.removeOrder();
        }
        return null;
    }

    public Ann_Game addGame(Ann_Game game) {
        return ann_GameDao.add(game);
    }

    public void updateGame(Ann_Game game) {
        ann_GameDao.update(game);
    }
}
