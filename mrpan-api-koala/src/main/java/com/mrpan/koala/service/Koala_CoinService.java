package com.mrpan.koala.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.koala.bean.Koala_Coin;

import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
public interface Koala_CoinService {
    public List<Koala_Coin> listCoins(List<FourObject> mapWhere);

    public Pager<Koala_Coin> findCoins(Integer pageNo, Integer pageSize, List<FourObject> mapWhere);

    Koala_Coin addCoin(Koala_Coin coin);

    void updateCoin(Koala_Coin coin);
}
