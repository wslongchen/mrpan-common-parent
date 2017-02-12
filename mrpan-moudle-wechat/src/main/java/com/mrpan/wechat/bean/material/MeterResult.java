package com.mrpan.wechat.bean.material;

/**
 * Created by mrpan on 2017/2/12.
 */
public class MeterResult {
    private String type; // 上传的媒体类型
    private String media_id; // 媒体文件上传后，获取时的唯一标识
    private int created_at; // 媒体上传成功的时间戳

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
}
