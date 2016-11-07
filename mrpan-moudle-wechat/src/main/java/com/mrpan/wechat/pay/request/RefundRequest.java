package com.mrpan.wechat.pay.request;

/**
 * 申请退款 请求参数
 */
public class RefundRequest extends BasePayParams {

	private String device_info; // 设备号
	private String transaction_id; // 微信订单号
	private String out_trade_no; // 商户订单号
	private String out_refund_no; // 商户退款单号
	private int total_fee; // 总金额
	private int refund_fee; // 退款金额
	private String refund_fee_type; // 货币种类
	private String op_user_id; // 操作员

	public RefundRequest() {
		super();
	}

	public RefundRequest(String deviceInfo, String transactionId,
			String outTradeNo, String outRefundNo, int totalFee, int refundFee,
			String refundFeeType, String opUserId) {
		super();
		device_info = deviceInfo;
		transaction_id = transactionId;
		out_trade_no = outTradeNo;
		out_refund_no = outRefundNo;
		total_fee = totalFee;
		refund_fee = refundFee;
		refund_fee_type = refundFeeType;
		op_user_id = opUserId;
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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int totalFee) {
		total_fee = totalFee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refundFee) {
		refund_fee = refundFee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refundFeeType) {
		refund_fee_type = refundFeeType;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String opUserId) {
		op_user_id = opUserId;
	}
/*
	@Override
	public Map<String, String> getParams() {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("device_info",this.device_info);
		params.put("transaction_id",this.transaction_id);
		params.put("out_trade_no",this.out_trade_no);
		params.put("total_fee",String.valueOf(this.total_fee));
		params.put("refund_fee",String.valueOf(this.refund_fee));
		params.put("refund_fee_type",this.refund_fee_type);
		params.put("op_user_id",this.op_user_id);
		params.putAll(getCommonParams());
		return params;
	}*/
}
