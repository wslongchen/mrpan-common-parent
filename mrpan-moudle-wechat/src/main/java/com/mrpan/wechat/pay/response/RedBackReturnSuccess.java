package com.mrpan.wechat.pay.response;

/**
 * 发送红包 return_code为succes时返回的操作
 */
public class RedBackReturnSuccess {
	private String sign; // 签名
	private String result_code; // 业务结果
	private String err_code; // 错误代码(可选)
	private String err_code_des; // 错误代码描述

	public RedBackReturnSuccess() {
		super();
	}

	public RedBackReturnSuccess(String sign, String result_code,
			String err_code, String err_code_des) {
		super();
		this.sign = sign;
		this.result_code = result_code;
		this.err_code = err_code;
		this.err_code_des = err_code_des;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
}
