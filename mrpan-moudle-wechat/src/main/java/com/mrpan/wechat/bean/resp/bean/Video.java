package com.mrpan.wechat.bean.resp.bean;

/**
 * 视频消息
 */
public class Video {
	private String MediaId; // 通过上传多媒体文件，得到的id
	private String Title; // 视频消息的标题
	private String Description; // 视频消息的描述

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
