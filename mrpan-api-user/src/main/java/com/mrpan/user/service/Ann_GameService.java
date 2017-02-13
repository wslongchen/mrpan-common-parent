package com.mrpan.user.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.user.bean.Ann_Cash;
import com.mrpan.user.bean.Ann_Game;

import java.util.List;

/**
 * Created by mrpan on 2017/2/13.
 */
public interface Ann_GameService {

    public List<Ann_Game> listGames(List<FourObject> mapWhere);

    public Pager<Ann_Game> findGames(Integer pageNo, Integer pageSize, List<FourObject> mapWhere);

    Ann_Game addGame(Ann_Game game);

    void updateGame(Ann_Game game);


}
