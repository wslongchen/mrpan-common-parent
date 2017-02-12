package com.mrpan.wechat.bean.material;

/**
 * Created by mrpan on 2017/2/12.
 */
public class MaterMsg {
    private int errcode; // 错误码
    private String errmsg; // 错误的提示信息

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
