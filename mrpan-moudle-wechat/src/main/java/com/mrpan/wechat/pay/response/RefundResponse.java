package com.mrpan.wechat.pay.response;

import com.mrpan.wechat.pay.request.BasePayParams;

/**
 * 申请退款
 */
public class RefundResponse extends BasePayParams{

	private String result_code; // 业务结果
	private String err_code; // 错误代码
	private String err_code_des; // 错误代码描述
	private String device_info; // 设备号
	private String transaction_id; // 微信订单号
	private String out_trade_no; // 商户订单号
	private String out_refund_no; // 微信退款单号
	private String refund_channel; // 退款渠道
	private int refund_fee; // 退款金额
	private int total_fee; // 订单总金额
	private String fee_type; // 订单金额货币种类
	private int cash_fee; // 现金支付金额
	private int cash_refund_fee; // 现金退款金额
	private int coupon_refund_fee; // 代金券或立减优惠退款金额
	private int coupon_refund_count;// 代金券或立减优惠使用数量
	private String coupon_refund_id;// 代金券或立减优惠ID

	public RefundResponse() {
		super();
	}

	
	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String resultCode) {
		result_code = resultCode;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String errCode) {
		err_code = errCode;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String errCodeDes) {
		err_code_des = errCodeDes;
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

	public String getRefund_channel() {
		return refund_channel;
	}

	public void setRefund_channel(String refundChannel) {
		refund_channel = refundChannel;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refundFee) {
		refund_fee = refundFee;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int totalFee) {
		total_fee = totalFee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String feeType) {
		fee_type = feeType;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(int cashFee) {
		cash_fee = cashFee;
	}

	public int getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(int cashRefundFee) {
		cash_refund_fee = cashRefundFee;
	}

	public int getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(int couponRefundFee) {
		coupon_refund_fee = couponRefundFee;
	}

	public int getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(int couponRefundCount) {
		coupon_refund_count = couponRefundCount;
	}

	public String getCoupon_refund_id() {
		return coupon_refund_id;
	}

	public void setCoupon_refund_id(String couponRefundId) {
		coupon_refund_id = couponRefundId;
	}


}
