package com.mrpan.wechat.bean.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mrpan on 2016/11/5.
 */
public class UserOpenListInfo implements Serializable{
    private int total;
    private int count;
    private List<String> openid;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }
}
