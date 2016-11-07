package com.mrpan.wechat.pay.request;

/**
 * 关闭订单
 */
public class CloseorderRequest extends BasePayParams implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String out_trade_no; // 商户订单号

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
/*
	@Override
	public Map<String, String> getParams() {
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("out_trade_no",this.out_trade_no);
		params.putAll(getCommonParams());
		return params;
	}*/
}
