package com.mrpan.wechat.bean.menu;

/**
 * Created by mrpan on 2017/1/22.
 */
public class LocationSelect extends BasicMenu{
    private String key;
    private String type=Menu.LOCATION_SELECT;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
