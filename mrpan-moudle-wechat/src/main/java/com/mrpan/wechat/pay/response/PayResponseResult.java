package com.mrpan.wechat.pay.response;

/**
 * 支付返回的结果
 */
public class PayResponseResult {
	private String return_code; // 返回状态码
	private String return_msg; // 返回信息

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}
}
