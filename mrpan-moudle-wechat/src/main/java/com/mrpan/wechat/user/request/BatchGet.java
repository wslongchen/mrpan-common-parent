package com.mrpan.wechat.user.request;

/**
 * Created by mrpan on 2016/11/5.
 */
public class BatchGet implements java.io.Serializable{
    private String openid; // openid
    private String lang; // 语言

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
