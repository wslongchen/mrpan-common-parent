package com.mrpan.wechat.bean.report;

/**
 * Created by mrpan on 2017/1/22.
 */
public class UserAnlyse {
    private String ref_date; // 数据日期
    private int user_source; // 用户渠道
    private int news_user; // 新增用户的数量
    private int cancel_user; // 取消用户数量
    private int cumulate_user; // 总用户数量

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String refDate) {
        ref_date = refDate;
    }

    public int getUser_source() {
        return user_source;
    }

    public void setUser_source(int userSource) {
        user_source = userSource;
    }

    public int getNews_user() {
        return news_user;
    }

    public void setNews_user(int newsUser) {
        news_user = newsUser;
    }

    public int getCancel_user() {
        return cancel_user;
    }

    public void setCancel_user(int cancelUser) {
        cancel_user = cancelUser;
    }

    public int getCumulate_user() {
        return cumulate_user;
    }

    public void setCumulate_user(int cumulateUser) {
        cumulate_user = cumulateUser;
    }

    @Override
    public String toString() {
        return "UserAnlyseEntity [cancel_user=" + cancel_user
                + ", cumulate_user=" + cumulate_user + ", news_user="
                + news_user + ", ref_date=" + ref_date + ", user_source="
                + user_source + "]";
    }

}
