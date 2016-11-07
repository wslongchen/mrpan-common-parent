package com.mrpan.wechat.bean.resp;

import com.mrpan.wechat.bean.resp.bean.Video;

public class VideoMessage extends BaseMessage {
	private Video video;

	@Override
	public String setMsgType() {
		return "video";
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
