package com.mrpan.wechat.message.templet;

import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.bean.message.MessageResult;
import com.mrpan.wechat.message.MessageConn;

/**
 * 发送物流模板信息  (需要再完善器模板方法)	
 */
public class LogisticsThemple extends MessageConn {

	
	/**
	 * 订单包裹跟踪通知
	 * @param openid		         待接收信息的用户openId
	 * @param templeId		         模板id
	 * @param url				点击跳转的链接
	 * @param TopColor			标题的颜色
	 * @param ContnetColor		内容的颜色
	 * @param title				标题内容
	 * @param orderId			订单号
	 * @param packgeId			包裹单号
	 * @param playementInfo		付款信息
	 * @param carrierInfo		配送信息
	 * @param receiver			收件人
	 * @param remark			备注  
	 * @return (发送) 
	 *  您好，您的订单包裹已被您拒收
		订单号：C0-xxxxxxx-xxxxxxx
		包裹单号：xxxxxxxxxxxxxx
		付款信息：￥125.67  现金付款
		配送信息：1860000000
		收件人：邹先生
		如有疑问，请联系我们
	 */
	public MessageResult TOrderPackgerTrack(String accessToken, String openid, String templeId, String url, String TopColor, String ContnetColor, String title, String orderId, String packgeId, String playementInfo, String carrierInfo, String receiver, String remark){
		MessageResult result = null;
		TreeMap<String,TreeMap<String,String>> mremark1 = new TreeMap<String,TreeMap<String,String>>();
		mremark1.put("first",BaseTemplet.ThempleItem(title, ContnetColor));
		mremark1.put("order_id",BaseTemplet.ThempleItem(orderId, ContnetColor));
		mremark1.put("package_id",BaseTemplet.ThempleItem(packgeId, ContnetColor));
		mremark1.put("payment_info",BaseTemplet.ThempleItem(playementInfo, ContnetColor));
		mremark1.put("carrier_info",BaseTemplet.ThempleItem(carrierInfo, ContnetColor));
		mremark1.put("receiver",BaseTemplet.ThempleItem(receiver, ContnetColor));
		mremark1.put("remark",BaseTemplet.ThempleItem(remark, ContnetColor));
		//
		result = getCommonParams(accessToken,openid,templeId,url,TopColor,mremark1);
		return result;
	}
	
	
	/**
	 * 状态变化通知
	 * @param accessToken   授权token
	 * @param openid		待接收消息的用户id
	 * @param templeId		模板id,可通过api获取,也可通过微信后台中获取
	 * @param url			点击消息跳转的链接
	 * @param TopColor		标题的颜色
	 * @param ContnetColor	内容的颜色
	 * @param orderNumber	订单号码
	 * @param status		最新状态
	 * @param remark		备注
	 * @return  (发送消息示列)您的物流单号123456，最新状态为:
				已到达广州市中转中心。回复“M”查看更多信息
	 */
	public MessageResult TOrderStateChanage(String accessToken,String openid,String templeId,String url,String TopColor,String ContnetColor,String orderNumber,String status,String remark){	
		MessageResult result = null;
		TreeMap<String,TreeMap<String,String>> mremark1 = new TreeMap<String,TreeMap<String,String>>();
		mremark1.put("orderNumber",BaseTemplet.ThempleItem(orderNumber, ContnetColor));
		mremark1.put("status",BaseTemplet.ThempleItem(status, ContnetColor));
		mremark1.put("remark",BaseTemplet.ThempleItem(remark, ContnetColor));
		//
		result = getCommonParams(accessToken,openid,templeId,url,TopColor,mremark1);
		return result;
	}
	
	
	/**
	 * 进入收单状态
	 * @param accessToken	授权token
	 * @param openid		待接收通知的openId
	 * @param templeId		模板的id
	 * @param url			用户点击url展示的页面
	 * @param TopColor		标题的颜色
	 * @param ContnetColor	内容的颜色
	 * @param channel	          订单的提交方式     例如 : app下单/微信下单
	 * @param orderNumber	订单号码
	 * @param status	         订单的状态  例如:  收单状态
	 * @param sometimes	         该状态变化的时间
	 * @param remark	         备注
	 * @return
	 * 发送消息示列()
	 * 您通过微信提交的物流订单113234，现已进入收单状态，收派员kantzou将在今天下午三点上门收件。谢谢您的支持！
	 */
	public MessageResult TOrderStateConsigneeState(String accessToken,String openid,String templeId,String url,String TopColor,String ContnetColor,String channel,String orderNumber,String status,String sometimes,String remark){
		MessageResult result = null;
		TreeMap<String,TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
		params.put("channel",BaseTemplet.ThempleItem(channel, ContnetColor));
		params.put("orderNumber",BaseTemplet.ThempleItem(orderNumber, ContnetColor));
		params.put("state",BaseTemplet.ThempleItem(status, ContnetColor));
		params.put("remark",BaseTemplet.ThempleItem(remark, ContnetColor));
		params.put("doSomething",BaseTemplet.ThempleItem(sometimes, ContnetColor));
		result = getCommonParams(accessToken,openid,templeId,url,TopColor,params);
		return result;
	}
	
	
	
	
	/**
	 * 设置公共参数并将其转换为json格式的数据
	 * @return  MsgResult.errcode ==0 && MsgResult.errmsg=ok 表示成功
	 */
	private MessageResult getCommonParams(String accessToken,String openid,String templeId,String url,String topColor,TreeMap<String,TreeMap<String,String>> methodParams){
		MessageResult result = null;
		TreeMap<String,Object> params = new TreeMap<String,Object>();
		params.put("touser", openid);
		params.put("template_id", templeId);
		params.put("url", url);
		params.put("topcolor", topColor);
		params.put("data", methodParams);
		String data = JSONObject.toJSONString(params,true);
		result = sendTemplate(accessToken,data);
		return result;
	}
}
