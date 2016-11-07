package com.mrpan.wechat.user.request;

import java.util.List;

/**
 * Created by mrpan on 2016/11/5.
 */
public class BatchGetRequest {
    private List<BatchGet> user_list;

    public List<BatchGet> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<BatchGet> user_list) {
        this.user_list = user_list;
    }
}