package com.mrpan.wechat.pay.request;

/**
 * 测速上报接口
 */
public class ReportRequest extends BasePayParams {
	private String device_info; // 设备号
	private String interface_url; // 接口url
	private int execute_time; // 接口耗时
	private String return_code; // 返回状态码
	private String return_msg; // 返回信息
	private String result_code; // 业务结果
	private String err_code; // 错误代码
	private String err_code_des; // 错误代码描述
	private String out_trade_no; // 商户订单号
	private String user_ip; // 访问接口IP
	private String time; // 商户上报时间

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}

	public String getInterface_url() {
		return interface_url;
	}

	public void setInterface_url(String interfaceUrl) {
		interface_url = interfaceUrl;
	}

	public int getExecute_time() {
		return execute_time;
	}

	public void setExecute_time(int executeTime) {
		execute_time = executeTime;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String userIp) {
		user_ip = userIp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	/*@Override
	public Map<String, String> getParams() {
		Map<String,String> params = new TreeMap<String,String>();
		params.put("device_info",this.device_info);
		params.put("interface_url",this.interface_url);
		params.put("execute_time",String.valueOf(this.execute_time));
		params.put("return_code",this.return_code);
		params.put("return_msg",this.return_msg);
		params.put("result_code",this.result_code);
		params.put("err_code",this.err_code);
		params.put("err_code_des",this.err_code_des);
		params.put("out_trade_no",this.out_trade_no);
		params.put("user_ip",this.user_ip);
		params.put("time",this.time);
		params.putAll(getCommonParams());
		return params;
	}*/
}
