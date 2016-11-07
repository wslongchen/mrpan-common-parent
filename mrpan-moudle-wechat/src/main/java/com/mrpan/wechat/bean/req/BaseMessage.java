package com.mrpan.wechat.bean.req;

/**
 * 基础消息类
 */
public abstract class BaseMessage {
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private String MsgType = SetMsgType();		//消息类型 例如 /text/image
	private int CreateTime; //消息创建时间 （整型）
	private int MsgId; //消息id，64位整型

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
	public int getMsgId() {
		return MsgId;
	}

	public void setMsgId(int msgId) {
		MsgId = msgId;
	}

	/**
	 * 消息类型
	 * @return
	 */
	public abstract String SetMsgType();
}
