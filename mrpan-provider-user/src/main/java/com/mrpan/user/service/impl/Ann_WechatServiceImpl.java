package com.mrpan.user.service.impl;

import com.mrpan.user.bean.Ann_Wechat;
import com.mrpan.user.dao.Ann_WechatDao;
import com.mrpan.user.service.Ann_UserService;
import com.mrpan.user.service.Ann_WechatService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mrpan on 2017/1/23.
 */
@Component("ann_WechatService")
public class Ann_WechatServiceImpl implements Ann_WechatService {
    private static final Logger logger = LoggerFactory
            .getLogger(Ann_WechatServiceImpl.class);

    @Autowired
    private Ann_WechatDao ann_WechatDao;

    public Ann_Wechat getWechatConfig() {
        return ann_WechatDao.getWechat(1);
    }

    public void updateAccessToken(String token) {
        if(StringUtils.isNotBlank(token)){
            ann_WechatDao.updateAccessToken(token);
        }
    }
}
