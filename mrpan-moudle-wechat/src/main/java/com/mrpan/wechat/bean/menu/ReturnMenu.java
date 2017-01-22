package com.mrpan.wechat.bean.menu;

/**
 * Created by mrpan on 2017/1/22.
 */
public class ReturnMenu extends BasicMenu{
    private MenuAllInfo[] sub_button;

    public MenuAllInfo[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(MenuAllInfo[] subButton) {
        sub_button = subButton;
    }
}
