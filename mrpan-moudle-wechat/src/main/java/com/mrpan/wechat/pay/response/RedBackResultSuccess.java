package com.mrpan.wechat.pay.response;

/**
 * 发送红包时 return_code 和result_code都为SUCCESS的时候有返回的结果对象
 */
public class RedBackResultSuccess implements java.io.Serializable {
	private String mch_billno; // 商户单号
	private String mch_id; // 商户号
	private String wxappid; // 公众账号appid
	private String re_openid; // 用户openid
	private int total_amount; // 付款金额
	private String send_listid;//微信单号
	
	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public RedBackResultSuccess(String mch_billno, String mch_id,
			String wxappid, String re_openid, int total_amount) {
		super();
		this.mch_billno = mch_billno;
		this.mch_id = mch_id;
		this.wxappid = wxappid;
		this.re_openid = re_openid;
		this.total_amount = total_amount;
	}

}
