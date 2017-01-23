package com.mrpan.user.service;

import com.mrpan.user.bean.Ann_Wechat;

/**
 * Created by mrpan on 2017/1/23.
 */
public interface Ann_WechatService {
    /**
     * 获取默认配置
     * @return
     */
    public Ann_Wechat getWechatConfig();

    /**
     * 更新token
     * @param token
     */
    public void updateAccessToken(String token);
}
