package com.mrpan.user.service.impl;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.common.core.utils.QueryKit;
import com.mrpan.user.bean.Ann_Vpn;
import com.mrpan.user.dao.Ann_VpnDao;
import com.mrpan.user.dao.Ann_WechatDao;
import com.mrpan.user.service.Ann_VpnService;
import com.mrpan.user.service.Ann_WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mrpan on 2017/1/23.
 */
@Component("ann_VpnService")
public class Ann_VpnServiceImpl implements Ann_VpnService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_VpnServiceImpl.class);

    @Autowired
    private Ann_VpnDao ann_VpnDao;

    public Ann_Vpn getVpnInfo(List<FourObject> strWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(strWhere);
            List<Ann_Vpn> list=this.ann_VpnDao.listVpnInfos(jpq);
            if(list.size()>0){
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Ann_Vpn> listVpnInfoList(List<FourObject> strWhere) {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(strWhere);
            List<Ann_Vpn> list=this.ann_VpnDao.listVpnInfos(jpq);
            if(list.size()>0){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Ann_Vpn addVpnInfo(Ann_Vpn vpn) throws Exception {
        return this.ann_VpnDao.add(vpn);
    }

    public void deleteVpnInfo(List<FourObject> strWhere) throws Exception {
        String jpq= null;
        try {
            jpq = QueryKit.createQuerySqlByFour(strWhere);
            List<Ann_Vpn> list=this.ann_VpnDao.listVpnInfos(jpq);
            if(list.size()>0){
                for (Ann_Vpn vpn:list){
                    this.ann_VpnDao.delete(vpn.getVpnId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVpnInfo(Ann_Vpn vpn) throws Exception {
        this.ann_VpnDao.update(vpn);
    }
}
