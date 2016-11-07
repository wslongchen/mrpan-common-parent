package com.mrpan.wechat.bean.resp;

/**
 * 回复文本消息
 */
public abstract class BaseMessage {
	private String ToUserName;// 接收方帐号（收到的OpenID）
	private String FromUserName; // 开发者微信号
	private int CreateTime; // 消息创建时间 （整型)
	private String MsgType = setMsgType(); // 消息类型

	public abstract String setMsgType();

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public int getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
}
