package com.mrpan.wechat.bean.menu;

/**
 * Created by mrpan on 2017/1/22.
 */
public class ViewMenu extends BasicMenu{
    private String url;
    private String type=Menu.VIEW;


    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
