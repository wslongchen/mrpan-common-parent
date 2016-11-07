package com.mrpan.wechat.bean.req;

public class TextMessage extends BaseMessage{
	private String Content;		//文本消息
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
	
	@Override
	public String SetMsgType() {
		return "text";
	}

}