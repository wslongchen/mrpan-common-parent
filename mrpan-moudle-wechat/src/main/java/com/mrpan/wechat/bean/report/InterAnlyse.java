package com.mrpan.wechat.bean.report;

/**
 * Created by mrpan on 2017/1/22.
 */
public class InterAnlyse {
    private String ref_date; // 数据日期
    private int ref_hour; // 数据的小时
    private int callback_count; // 通过服务器配置地址获取消息后,被动回复用户消息的次数
    private int fail_count; // 上述动作的失败次数
    private int total_time_cost; // 总耗时
    private int max_time_cost; // 最大耗时

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

    public int getCallback_count() {
        return callback_count;
    }

    public void setCallback_count(int callbackCount) {
        callback_count = callbackCount;
    }

    public int getFail_count() {
        return fail_count;
    }

    public void setFail_count(int failCount) {
        fail_count = failCount;
    }

    public int getTotal_time_cost() {
        return total_time_cost;
    }

    public void setTotal_time_cost(int totalTimeCost) {
        total_time_cost = totalTimeCost;
    }

    public int getMax_time_cost() {
        return max_time_cost;
    }

    public void setMax_time_cost(int maxTimeCost) {
        max_time_cost = maxTimeCost;
    }

    @Override
    public String toString() {
        return "InterAnlyseEntity [callback_count=" + callback_count
                + ", fail_count=" + fail_count + ", max_time_cost="
                + max_time_cost + ", ref_date=" + ref_date + ", ref_hour="
                + ref_hour + ", total_time_cost=" + total_time_cost + "]";
    }
}
