package com.mrpan.koala.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.Pager;
import com.mrpan.koala.bean.Koala_Coin;
import com.mrpan.koala.bean.Koala_Step;

import java.util.List;

/**
 * Created by mrpan on 2017/2/19.
 */
public interface Koala_StepService {
    public List<Koala_Step> listSteps(List<FourObject> mapWhere);

    public Pager<Koala_Step> findSteps(Integer pageNo, Integer pageSize, List<FourObject> mapWhere);

    Koala_Step addStep(Koala_Step step);

    void updateStep(Koala_Step step);
}
