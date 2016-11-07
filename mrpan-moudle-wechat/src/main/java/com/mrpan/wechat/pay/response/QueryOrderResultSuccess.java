package com.mrpan.wechat.pay.response;

/**
 * 查询订单返回的结果
 */
public class QueryOrderResultSuccess {
	private String device_info;		//设备号
	private String openid;			//用户标识
	private String is_subscribe;	//是否关注公众号
	private String trade_type;		//交易类型
	private String trade_state;		//交易状态
	private String bank_type;		//付款银行
	private int total_fee;			//总金额(单位为分)
	private String fee_type;		//货币种类
	private int	cash_fee;			//现金支付金额
	private String cash_fee_type;	//现金支付类型
	private int coupon_fee;		//代金券或立减优惠金额;
	private int coupon_count;	//代金卷使用数量
	private String coupon_batch_id_$n;//代金券或立减优惠批次ID
	private String coupon_id_$n;	  //代金券或立减优惠ID
	private int coupon_fee_$n;		  //单个代金券或立减优惠支付金额
	private String transaction_id;	  //微信支付订单号
	private String out_trade_no;	  //商户订单号
	private String attach;			  //商家数据包
	private String time_end;		  //支付完成时间
	private String trade_state_desc;  //交易状态描述
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String isSubscribe) {
		is_subscribe = isSubscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String tradeState) {
		trade_state = tradeState;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bankType) {
		bank_type = bankType;
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
	public int getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(int couponFee) {
		coupon_fee = couponFee;
	}
	public int getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(int couponCount) {
		coupon_count = couponCount;
	}
	public String getCoupon_batch_id_$n() {
		return coupon_batch_id_$n;
	}
	public void setCoupon_batch_id_$n(String couponBatchId_$n) {
		coupon_batch_id_$n = couponBatchId_$n;
	}
	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}
	public void setCoupon_id_$n(String couponId_$n) {
		coupon_id_$n = couponId_$n;
	}
	public int getCoupon_fee_$n() {
		return coupon_fee_$n;
	}
	public void setCoupon_fee_$n(int couponFee_$n) {
		coupon_fee_$n = couponFee_$n;
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
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String timeEnd) {
		time_end = timeEnd;
	}
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	public void setTrade_state_desc(String tradeStateDesc) {
		trade_state_desc = tradeStateDesc;
	}
}
