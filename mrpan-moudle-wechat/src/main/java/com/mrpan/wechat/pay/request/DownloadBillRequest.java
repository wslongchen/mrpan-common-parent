package com.mrpan.wechat.pay.request;

/**
 * 下载对账单
 */
public class DownloadBillRequest extends BasePayParams{

	private String device_info; // 设备号
	private String bill_date; // 对账单日期
	private String bill_type; // 账单类型

	public DownloadBillRequest() {
		super();
	}

	public DownloadBillRequest(String deviceInfo, String billDate,
			String billType) {
		super();
		device_info = deviceInfo;
		bill_date = billDate;
		bill_type = billType;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String billDate) {
		bill_date = billDate;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String billType) {
		bill_type = billType;
	}

	/*@Override
	public Map<String, String> getParams() {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("device_info",this.device_info);
		params.put("bill_date",this.bill_date);
		params.put("bill_type",this.bill_type);
		params.putAll(getCommonParams());
		return params;
	}*/
}
