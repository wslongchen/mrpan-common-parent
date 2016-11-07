package com.mrpan.wechat.pay.request;


/**
 * 统一下单实体对象
 */
public class UnifiedOrderRequest extends BasePayParams implements
		java.io.Serializable {
	private String device_info; // (设备号)
	private String body; // 商品描述
	private String detail; // 商品详情
	private String attach; // 附加数据
	private String out_trade_no; // 商户订单号
	private String fee_type="CNY"; // 货币类型	默认为人民币
	private String total_fee; // 总金额 传入int型的数据
	private String spbill_create_ip;// 终端ip
	private String time_start; // 起始时间
	private String time_expire; // 结束时间
	private String goods_tag; // 商品标记
	private String notify_url; // 通知url
	private String trade_type; // 交易类型
	private String product_id; // 商品id
	private String openid; // 用户标识(trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识)

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String feeType) {
		fee_type = feeType;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String timeStart) {
		time_start = timeStart;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String timeExpire) {
		time_expire = timeExpire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goodsTag) {
		goods_tag = goodsTag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String productId) {
		product_id = productId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/*@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("device_info", this.device_info);
		params.put("body", this.body);
		params.put("detail", this.detail);
		params.put("attach", this.attach);
		params.put("out_trade_no", this.out_trade_no);
		params.put("fee_type", this.fee_type);
		params.put("total_fee", String.valueOf(this.total_fee));
		params.put("spbill_create_ip", this.spbill_create_ip);
		params.put("time_start", this.time_start);
		params.put("time_expire", this.time_expire);
		params.put("goods_tag", this.goods_tag);
		params.put("notify_url", this.notify_url);
		params.put("trade_type", this.trade_type);
		params.put("product_id", this.product_id);
		params.put("openid", this.openid);
		params.putAll(getCommonParams());
		return params;
	}*/
}
