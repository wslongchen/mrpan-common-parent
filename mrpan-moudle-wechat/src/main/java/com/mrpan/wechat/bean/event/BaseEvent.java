package com.mrpan.wechat.bean.event;

/**
 * 事件基础类
 */
public abstract class BaseEvent {
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private int CreateTime; // 消息创建时间 （整型）
	private String MsgType = "event"; // 消息类型，event
	private String Event =setEvent(); // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)

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

	public String getMsgType() {
		return MsgType;
	}

	public abstract String setEvent();
}
