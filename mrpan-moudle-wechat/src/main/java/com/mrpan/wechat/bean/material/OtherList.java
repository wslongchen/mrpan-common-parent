package com.mrpan.wechat.bean.material;

import java.util.List;

/**
 * Created by mrpan on 2017/2/12.
 */
public class OtherList {
    private int total_count;
    private int item_count;
    private List<OtherItem> list; // 素材的数量

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public List<OtherItem> getList() {
        return list;
    }

    public void setList(List<OtherItem> list) {
        this.list = list;
    }
}
