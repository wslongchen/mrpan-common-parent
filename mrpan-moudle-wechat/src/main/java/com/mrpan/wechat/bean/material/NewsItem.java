package com.mrpan.wechat.bean.material;

import com.mrpan.wechat.bean.message.NewsEntity;

import java.util.List;


/**
 * Created by mrpan on 2017/2/12.
 */
public class NewsItem {
    private String media_id;		//media编号
    private List<NewsEntity> newsItem;	//图文消息的list集合
    private String update_time;		//更新的时间

    public List<NewsEntity> getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(List<NewsEntity> newsItem) {
        this.newsItem = newsItem;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
