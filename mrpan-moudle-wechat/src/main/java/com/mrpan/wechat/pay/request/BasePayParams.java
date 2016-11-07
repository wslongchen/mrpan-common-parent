package com.mrpan.wechat.pay.request;

import com.mrpan.wechat.pay.annotation.NotSign;

/**
 * 请求参数的基类
 */
public class BasePayParams {
	@NotSign
	public static final String DEVICEINFO="WEB";				//设备号   PC网页或公众号内支付请传"WEB"
	@NotSign
	public static final String TIMEFORMAT="yyyyMMddHHmmss";		//订单时间格式
	private String appid; // 公众号id
	private String mch_id; // 商户号
	private String nonce_str; // 随机字符串
	private String sign; // 随机字符串
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
