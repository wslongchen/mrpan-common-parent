package com.mrpan.wechat.pay.response;


/**
 * js验证回调参数
 */
public class JSResponse {
	private String timestamp; // 时间戳
	private String nonceStr; // 随机字符串
	private String signature; // 签名
	private String appId; // 应用的appId
	private String ticket; // 换取的ticket
	private String url; // url 注意此处应该与公众号中设置的一致

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
