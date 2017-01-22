package com.mrpan.wechat.bean.menu;

/**
 * Created by mrpan on 2017/1/22.
 */
public class PicWeixin extends BasicMenu {
    private String key;
    private String sub_button[];
    private String type =Menu.PIC_WEIXIN;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(String[] sub_button) {
        this.sub_button = sub_button;
    }
}
