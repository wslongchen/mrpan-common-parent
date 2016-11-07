package com.mrpan.wechat.pay.response;

import com.mrpan.wechat.pay.request.BasePayParams;

/**
 * 查询退款的返回结果集 
 */
public class RefundqueryResponse extends BasePayParams{
	private String result_code; // 业务结果
	private String err_code; // 错误码
	private String err_code_des;// 错误描述
	private String device_info; // 设备号
	private String transaction_id; // 微信订单号
	private String out_trade_no; // 商户订单号
	private int total_fee; // 订单总金额
	private String fee_type; // 订单金额货币种类
	private int cash_fee; // 现金支付金额
	private String cash_fee_type; // 货币种类
	private int refund_fee; // 退款金额
	private int coupon_refund_fee; // 代金券或立减优惠退款金额
	private int refund_count; // 退款笔数
	private String out_refund_no_$n;// 商户退款单号
	private String refund_id_$n; // 微信退款单号
	private String refund_channel_$n; // 退款渠道
	private int refund_fee_$n; // 退款金额
	private String fee_type_$n; // 货币种类
	private int coupon_refund_fee_$n; // 代金券或立减优惠退款金额
	private int coupon_refund_count_$n; // 代金券或立减优惠使用数量
	private String coupon_refund_batch_id_$n_$m; // 代金券或立减优惠批次ID
	private String coupon_refund_id_$n_$m; // 代金券或立减优惠ID
	private int coupon_refund_fee_$n_$m; // 单个代金券或立减优惠支付金额
	private int refund_status_$n; // 退款状态

	public RefundqueryResponse() {
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

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cashFeeType) {
		cash_fee_type = cashFeeType;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refundFee) {
		refund_fee = refundFee;
	}

	public int getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(int couponRefundFee) {
		coupon_refund_fee = couponRefundFee;
	}

	public int getRefund_count() {
		return refund_count;
	}

	public void setRefund_count(int refundCount) {
		refund_count = refundCount;
	}

	public String getOut_refund_no_$n() {
		return out_refund_no_$n;
	}

	public void setOut_refund_no_$n(String outRefundNo_$n) {
		out_refund_no_$n = outRefundNo_$n;
	}

	public String getRefund_id_$n() {
		return refund_id_$n;
	}

	public void setRefund_id_$n(String refundId_$n) {
		refund_id_$n = refundId_$n;
	}

	public String getRefund_channel_$n() {
		return refund_channel_$n;
	}

	public void setRefund_channel_$n(String refundChannel_$n) {
		refund_channel_$n = refundChannel_$n;
	}

	public int getRefund_fee_$n() {
		return refund_fee_$n;
	}

	public void setRefund_fee_$n(int refundFee_$n) {
		refund_fee_$n = refundFee_$n;
	}

	public String getFee_type_$n() {
		return fee_type_$n;
	}

	public void setFee_type_$n(String feeType_$n) {
		fee_type_$n = feeType_$n;
	}

	public int getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}

	public void setCoupon_refund_fee_$n(int couponRefundFee_$n) {
		coupon_refund_fee_$n = couponRefundFee_$n;
	}

	public int getCoupon_refund_count_$n() {
		return coupon_refund_count_$n;
	}

	public void setCoupon_refund_count_$n(int couponRefundCount_$n) {
		coupon_refund_count_$n = couponRefundCount_$n;
	}

	public String getCoupon_refund_batch_id_$n_$m() {
		return coupon_refund_batch_id_$n_$m;
	}

	public void setCoupon_refund_batch_id_$n_$m(String couponRefundBatchId_$n_$m) {
		coupon_refund_batch_id_$n_$m = couponRefundBatchId_$n_$m;
	}

	public String getCoupon_refund_id_$n_$m() {
		return coupon_refund_id_$n_$m;
	}

	public void setCoupon_refund_id_$n_$m(String couponRefundId_$n_$m) {
		coupon_refund_id_$n_$m = couponRefundId_$n_$m;
	}

	public int getCoupon_refund_fee_$n_$m() {
		return coupon_refund_fee_$n_$m;
	}

	public void setCoupon_refund_fee_$n_$m(int couponRefundFee_$n_$m) {
		coupon_refund_fee_$n_$m = couponRefundFee_$n_$m;
	}

	public int getRefund_status_$n() {
		return refund_status_$n;
	}

	public void setRefund_status_$n(int refundStatus_$n) {
		refund_status_$n = refundStatus_$n;
	}




}
