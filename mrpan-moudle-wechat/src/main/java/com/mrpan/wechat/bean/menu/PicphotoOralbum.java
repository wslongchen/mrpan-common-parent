package com.mrpan.wechat.bean.menu;

/**
 * Created by mrpan on 2017/1/22.
 */
public class PicphotoOralbum extends BasicMenu {
    private String key;
    private String sub_button[] = new String[]{};
    private String type =Menu.PIC_PHOTO_OR_ALBUM;

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
}
