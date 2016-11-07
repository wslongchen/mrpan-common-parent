package com.mrpan.wechat.bean.resp;


import com.mrpan.wechat.bean.resp.bean.Music;

public class MusicMessage extends BaseMessage {
	private Music music;

	@Override
	public String setMsgType() {
		return "music";
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}