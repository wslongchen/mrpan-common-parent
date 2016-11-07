package com.mrpan.wechat.pay;

import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.Connection;
import com.mrpan.wechat.bean.results.WechatResult;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.pay.handler.PayHandler;
import com.mrpan.wechat.pay.request.UnifiedOrderRequest;
import com.mrpan.wechat.pay.response.TicketResponse;
import com.mrpan.wechat.pay.response.UnifiedOrderResponse;

/**
 * 下单连接工具类
 */
public class PayConn{
	
	//js 初始化验证
	private static final String GETTICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	//在线下单
	private static final String PAYORDER="https://api.mch.weixin.qq.com/pay/unifiedorder";
	//查询订单
	private static final String QUERYORDER="https://api.mch.weixin.qq.com/pay/orderquery";
	//关闭订单
	private static final String CLOSEORDER="https://api.mch.weixin.qq.com/pay/closeorder";
	//申请退款(需要证书)
	private static final String REFUNDORDER="https://api.mch.weixin.qq.com/secapi/pay/refund";
	//查询退款
	private static final String REFUNDQUERY="https://api.mch.weixin.qq.com/pay/refundquery"; 
	//下载对账单
	private static final String DOWNLOADBILL="https://api.mch.weixin.qq.com/pay/downloadbill";
	//测速上报
	private static final String REPORT="https://api.mch.weixin.qq.com/payitil/report";
	//转换短链接
	private static final String SHORTURL="https://api.mch.weixin.qq.com/tools/shorturl";
	
	
	/**
	 * 在线下单的方法
	 * @param request 	统一下单请求
	 * @return			Result.msg 表示返回的原始json格式数据      result.success=true表示是否成功标识    result.obj为 UnifiedOrderResponse对象
	 */
	public static WechatResult payOrder(UnifiedOrderRequest request){
		WechatResult resultObj = new WechatResult();
		Connection conn = Connection.getInstance("pay");
		String data = PayHandler.unifiedorder(request);
		String result = conn.HttpsDefaultExecute("POST", PAYORDER,null,data);
		UnifiedOrderResponse response = PayHandler.unifiedOrderResponse(result);
		resultObj.setMsg(result);
		resultObj.setSuccess(true);
		resultObj.setObj(response);
		return resultObj;
	}
	
	/**
	 *  根据授权token获取js令牌
	 * @param accessToken	授权token
	 * @return	Result.msg 表示返回的原始json格式数据      result.success=true表示是否成功标识    result.obj为 TicketResponse对象
	 */
	public static WechatResult getTicket(String accessToken){
		WechatResult resultObj = new WechatResult();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("access_token",accessToken);
		params.put("type","jsapi");
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("GET", GETTICKET,params,"");
		JSONObject object = JSONObject.parseObject(result);
		if(object.containsKey("ticket")){
			TicketResponse resposne = ConvertJsonUtils.jsonToJavaObject(result,TicketResponse.class);
			resultObj.setObj(resposne);
			resultObj.setSuccess(true);
		}else{
			resultObj.setObj("");
			resultObj.setSuccess(false);
		}
		resultObj.setMsg(result);
		return resultObj;
	}
	
	/**
	 * 查询订单
	 * @return
	 */
	public String queryOrder(){
		String data = "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", QUERYORDER,null, data);
		return result;
	}
	
	/**
	 * 关闭订单
	 * @return
	 */
	public String closeOrder(){
		String data = "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", CLOSEORDER,null, data);
		return result;
	}
	
	/**
	 * 申请退款
	 * @return
	 */
	public String refundOrder(){
		String data= "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", REFUNDORDER,null, data);
		return result;
	}
	
	/**
	 * 查询退款
	 * @return
	 */
	public String refundQuery(){
		String data= "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", REFUNDQUERY,null, data);
		return result;
	}
	
	/**
	 * 下载对账单
	 * @return
	 */
	public String downloadbill(){
		String data= "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", DOWNLOADBILL,null, data);
		return result;
	}
	
	/**
	 * 测速上报接口
	 * @return
	 */
	public String report(){
		String data= "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST", REPORT,null, data);
		return result;
	}
	
	/**
	 * 转换短链接
	 * @return
	 */
	public String shortUrl(){
		String data= "";
		Connection conn = Connection.getInstance("pay");
		String result = conn.HttpsDefaultExecute("POST",SHORTURL,null, data);
		return result;
	}
}
