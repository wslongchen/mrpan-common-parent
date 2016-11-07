package com.mrpan.wechat.pay.request;

/**
 * 查询退款请求对象
 */
public class RefundqueryRequest extends BasePayParams{

	private String device_info; // 设备号
	private String transaction_id; // 微信订单号
	private String out_trade_no; // 商户订单号
	private String out_refund_no; // 商户退款单号
	private String refund_id; // 微信退款单号

	public RefundqueryRequest() {
		super();
	}

	public RefundqueryRequest(String deviceInfo, String transactionId,
			String outTradeNo, String outRefundNo, String refundId) {
		super();
		device_info = deviceInfo;
		transaction_id = transactionId;
		out_trade_no = outTradeNo;
		out_refund_no = outRefundNo;
		refund_id = refundId;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}

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

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String outRefundNo) {
		out_refund_no = outRefundNo;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refundId) {
		refund_id = refundId;
	}

	/*@Override
	public Map<String, String> getParams() {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("device_info",this.device_info);
		params.put("transaction_id",this.transaction_id);
		params.put("out_trade_no",this.out_trade_no);
		params.put("out_refund_no",this.out_refund_no);
		params.put("refund_id",this.refund_id);
		params.putAll(getCommonParams());
		return params;
	}*/
}
