package com.mrpan.wechat.bean.report;

/**
 * Created by mrpan on 2017/1/22.
 */
public class MsgAnalyse {
    private String ref_date; // 数据的日期，需在begin_date和end_date之间
    private int ref_hour; // 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
    private int msg_type; // 消息的类型 消息类型，代表含义如下：1代表文字 2代表图片 3代表语音 4代表视频// 6代表第三方应用消息（链接消息）
    private int msg_user; // 上行发送了（向公众号发送了）消息的用户数
    private int msg_count; // 上行发送了消息的消息总数
    private int count_interval; // 当日发送消息量分布的区间，0代表// “0”，1代表“1-5”，2代表“6-10”，3代表“10次以上”
    private int int_page_read_count; // 图文页的阅读次数
    private int ori_page_read_user; // 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0

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

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msgType) {
        msg_type = msgType;
    }

    public int getMsg_user() {
        return msg_user;
    }

    public void setMsg_user(int msgUser) {
        msg_user = msgUser;
    }

    public int getMsg_count() {
        return msg_count;
    }

    public void setMsg_count(int msgCount) {
        msg_count = msgCount;
    }

    public int getCount_interval() {
        return count_interval;
    }

    public void setCount_interval(int countInterval) {
        count_interval = countInterval;
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

    @Override
    public String toString() {
        String result="";
        result="Date-->"+ref_date+"\nref_hour-->"+ref_hour+"\nmsg_type-->"+msg_type+"\nmsg_user-->"+msg_user+"\nmsg_count-->"+msg_count
                +"\ncount_interval-->"+count_interval+"\nint_page_read_count-->"+int_page_read_count+"\nori_page_read_user--->"+ori_page_read_user;
        System.out.println(result);
        return result;
    }
}
