package com.mrpan.wechat.pay.request;

/**
 * 微信发红包请求参数
 */
public class RedBackRequest implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nonce_str; // 随机字符串
	private String sign; // 签名
	private String mch_billno; // 商户订单号
	private String mch_id; // 商户号
	private String sub_mch_id; // 子商户号(可选)
	private String wxappid; // 公众账号appid
	private String nick_name; // 提供方账号
	private String send_name; // 商户名称
	private String re_openid; // 用户openid
	private int total_amount; // 付款金额
	private int min_value; // 最小红包金额
	private int max_value; // 最大红包金额
	private int total_num; // 红包发送总人数
	private String wishing; // 红包祝福语
	private String client_ip; // Ip地址
	private String act_name; // 活动名称
	private String remark; // 备注
	private String logo_imgurl; // 商户logo的url(可选)
	private String share_content; // 分享方案(可选)
	private String share_url; // 分享链接 (可选)
	private String share_imgurl; // 分享的图片(可选)
	private String scene_id;//场景id(可选)PRODUCT_1:商品促销,PRODUCT_2:抽奖,PRODUCT_3:虚拟物品兑奖 ,PRODUCT_4:企业内部福利,PRODUCT_5:渠道分润,PRODUCT_6:保险回馈,PRODUCT_7:彩票派奖,PRODUCT_8:税务刮奖
	//posttime:用户操作的时间戳
	//mobile:业务系统账号的手机号，国家代码-手机号。不需要+号
	//deviceid :mac 地址或者设备唯一标识 
	//clientversion :用户操作的客户端版本
	//把值为非空的信息用key=value进行拼接，再进行urlencode
	//urlencode(posttime=xx& mobile =xx&deviceid=xx)
	private String risk_info;//活动信息(可选)
	private String consume_mch_id;//资金授权商户号（可选）
	
	public RedBackRequest() {
		super();
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

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

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
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

	public int getMin_value() {
		return min_value;
	}

	public void setMin_value(int min_value) {
		this.min_value = min_value;
	}

	public int getMax_value() {
		return max_value;
	}

	public void setMax_value(int max_value) {
		this.max_value = max_value;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogo_imgurl() {
		return logo_imgurl;
	}

	public void setLogo_imgurl(String logo_imgurl) {
		this.logo_imgurl = logo_imgurl;
	}

	public String getShare_content() {
		return share_content;
	}

	public void setShare_content(String share_content) {
		this.share_content = share_content;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public String getShare_imgurl() {
		return share_imgurl;
	}

	public void setShare_imgurl(String share_imgurl) {
		this.share_imgurl = share_imgurl;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getRisk_info() {
		return risk_info;
	}

	public void setRisk_info(String risk_info) {
		this.risk_info = risk_info;
	}

	public String getConsume_mch_id() {
		return consume_mch_id;
	}

	public void setConsume_mch_id(String consume_mch_id) {
		this.consume_mch_id = consume_mch_id;
	}
	
}
