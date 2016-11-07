package com.mrpan.wechat.bean.req;

/**
 * 消息基类
 */
public class BaseMessageUserInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private long CreateTime; // 消息创建时间 （整型）
	private long MsgId; // 消息id，64位整型

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

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}
