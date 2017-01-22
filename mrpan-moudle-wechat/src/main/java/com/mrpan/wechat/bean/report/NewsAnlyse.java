package com.mrpan.wechat.bean.report;

/**
 * Created by mrpan on 2017/1/22.
 */
public class NewsAnlyse {
    private String ref_date; // 数据的日期 需在begin_date和end_date之间
    private int ref_hour; // 数据的小时
    private String stat_date; // 统计的日期
    private String msgid; // 消息的id
    private String title; // 图文消息的标题
    private int int_page_read_user; // 图文页的阅读人数
    private int int_page_read_count; // 图文页的统计次数
    private int ori_page_read_user; // 原文页的阅读次数，
    private int ori_page_read_count; // 原文页的阅读次数
    private int share_scene; // 分享场景
    private int share_user; // 分享的人数
    private int share_count; // 分享的次数
    private int add_to_fav_user; // 收藏的人数
    private int add_to_fav_count; // 收藏的次数
    private int target_user; // 送达的人数

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String refDate) {
        ref_date = refDate;
    }

    public int getRef_hour() {
        return ref_hour;
    }

    public void setRef_hour(int refHour) {
        ref_hour = refHour;
    }

    public String getStat_date() {
        return stat_date;
    }

    public void setStat_date(String statDate) {
        stat_date = statDate;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInt_page_read_user() {
        return int_page_read_user;
    }

    public void setInt_page_read_user(int intPageReadUser) {
        int_page_read_user = intPageReadUser;
    }

    public int getInt_page_read_count() {
        return int_page_read_count;
    }

    public void setInt_page_read_count(int intPageReadCount) {
        int_page_read_count = intPageReadCount;
    }

    public int getOri_page_read_user() {
        return ori_page_read_user;
    }

    public void setOri_page_read_user(int oriPageReadUser) {
        ori_page_read_user = oriPageReadUser;
    }

    public int getOri_page_read_count() {
        return ori_page_read_count;
    }

    public void setOri_page_read_count(int oriPageReadCount) {
        ori_page_read_count = oriPageReadCount;
    }

    public int getShare_scene() {
        return share_scene;
    }

    public void setShare_scene(int shareScene) {
        share_scene = shareScene;
    }

    public int getShare_user() {
        return share_user;
    }

    public void setShare_user(int shareUser) {
        share_user = shareUser;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int shareCount) {
        share_count = shareCount;
    }

    public int getAdd_to_fav_user() {
        return add_to_fav_user;
    }

    public void setAdd_to_fav_user(int addToFavUser) {
        add_to_fav_user = addToFavUser;
    }

    public int getAdd_to_fav_count() {
        return add_to_fav_count;
    }

    public void setAdd_to_fav_count(int addToFavCount) {
        add_to_fav_count = addToFavCount;
    }

    public int getTarget_user() {
        return target_user;
    }

    public void setTarget_user(int targetUser) {
        target_user = targetUser;
    }

    @Override
    public String toString() {
        return "NewsAnlyseEntity [add_to_fav_count=" + add_to_fav_count
                + ", add_to_fav_user=" + add_to_fav_user
                + ", int_page_read_count=" + int_page_read_count
                + ", int_page_read_user=" + int_page_read_user + ", msgid="
                + msgid + ", ori_page_read_count=" + ori_page_read_count
                + ", ori_page_read_user=" + ori_page_read_user + ", ref_date="
                + ref_date + ", ref_hour=" + ref_hour + ", share_count="
                + share_count + ", share_scene=" + share_scene
                + ", share_user=" + share_user + ", stat_date=" + stat_date
                + ", target_user=" + target_user + ", title=" + title + "]";
    }
}
