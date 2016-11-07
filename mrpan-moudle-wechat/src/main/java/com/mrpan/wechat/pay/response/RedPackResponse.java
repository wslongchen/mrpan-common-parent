package com.mrpan.wechat.pay.response;

public class RedPackResponse {
	private String sign; // 签名
	private String result_code; // 业务结果
	private String err_code; // 错误代码(可选)
	private String err_code_des; // 错误代码描述
	private String mch_billno; // 商户单号
	private String mch_id; // 商户号
	private String wxappid; // 公众账号appid
	private String re_openid; // 用户openid
	private int total_amount; // 付款金额
	private String send_listid;//微信单号
	
}
