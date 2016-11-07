package com.mrpan.wechat.pay.request;

/**
 * 查询订单
 */
public class QueryOrderRequet extends BasePayParams implements
		java.io.Serializable {

	private String transaction_id; // 微信订单号
	private String out_trade_no; // 商户订单号

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	/*@Override
	public Map<String, String> getParams() {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("transaction_id",this.transaction_id);
		params.put("out_trade_no",this.out_trade_no);
		params.putAll(getCommonParams());
		return params;
	}*/

}
