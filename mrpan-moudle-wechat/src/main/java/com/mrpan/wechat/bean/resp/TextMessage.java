package com.mrpan.wechat.bean.resp;

public class TextMessage extends BaseMessage {
	private String Content;

	@Override
	public String setMsgType() {
		return "text";
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}