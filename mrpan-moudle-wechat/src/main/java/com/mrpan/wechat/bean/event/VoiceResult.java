package com.mrpan.wechat.bean.event;

/**
 * 语音识别
 */
public class VoiceResult {
	private String ToUserName; // 开发者微信号
	private String FromUserName; // 发送方帐号（一个OpenID）
	private int CreateTime; // 消息创建时间 （整型）
	private String MsgType = "voice"; // 消息类型
	private String MediaID; // 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
	private String Format = "amr"; // 语音格式：amr
	private String Recognition; // 语音识别结果，UTF8编码
	private int MsgID; // 消息id

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

	public String getMediaID() {
		return MediaID;
	}

	public void setMediaID(String mediaID) {
		MediaID = mediaID;
	}

	public String getFormat() {
		return Format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public int getMsgID() {
		return MsgID;
	}

	public void setMsgID(int msgID) {
		MsgID = msgID;
	}

}
