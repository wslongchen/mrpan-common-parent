package com.mrpan.wechat.bean.resp;


import com.mrpan.wechat.bean.resp.bean.Voice;

public class VoiceMessage extends BaseMessage {
	private Voice voice;

	@Override
	public String setMsgType() {
		return "voice";
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}
}
