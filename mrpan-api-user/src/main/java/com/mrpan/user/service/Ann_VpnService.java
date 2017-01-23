package com.mrpan.user.service;

import com.mrpan.common.core.utils.FourObject;
import com.mrpan.user.bean.Ann_Vpn;

import java.util.List;

/**
 * Created by mrpan on 2017/1/23.
 */
public interface Ann_VpnService {
    /**
     * 条件查询vpn情况
     * @param strWhere
     * @return
     */
    public Ann_Vpn getVpnInfo(List<FourObject> strWhere);

    /**
     * 条件查询vpn列表
     * @param strWhere
     * @return
     */
    public List<Ann_Vpn> listVpnInfoList(List<FourObject> strWhere);

    /**
     * 新增Vpn申请
     * @param vpn
     * @return
     * @throws Exception
     */
    public Ann_Vpn addVpnInfo(Ann_Vpn vpn) throws Exception;

    /**
     * 条件删除申请
     * @param strWhere
     * @throws Exception
     */
    public void deleteVpnInfo(List<FourObject> strWhere) throws Exception;

    public void updateVpnInfo(Ann_Vpn vpn) throws Exception;
}
