package com.mrpan.wechat.bean.results.handler.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.mrpan.wechat.bean.req.BaseMessage;
import com.mrpan.wechat.bean.req.BaseMessageUserInfo;
import com.mrpan.wechat.bean.results.handler.BaseXmlHandler;
import com.mrpan.wechat.bean.results.handler.ReqMessageHandler;
import com.mrpan.wechat.bean.results.utils.ConvertXMLUtils;
import org.dom4j.DocumentException;

/**
 * 默认请求消息处理类
 */
public abstract class DefaultReqMessageDispose implements ReqMessageHandler {

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

	/**
	 * 请求消息类型: 短视频消息
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型: view(自定义菜单view事件)
	 */
	public static final String EVENT_TYPE_VIEW="VIEW";
	
	/**
	 * 事件类型:scan(用户已关注时的事件推送)
	 */
	public static final String EVENT_TYPE_SCAN="SCAN";
	
	/**
	 * 事件类型:LOCATION(上报地理位置事件)
	 */
	public static final String EVENT_TYPE_LOCATION="LOCATION";
	
	
	/**
	 * 默认处理类
	 * @param input
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public String DefaultMsgDisPose(InputStream input) throws DocumentException, IOException{
		String result="";
		if(input!=null){
			Map<String,String> params = ConvertXMLUtils.parseXml(input);
			if(params!=null&&params.size()>0){
				BaseMessageUserInfo msgInfo = new BaseMessageUserInfo();
				String createTime = params.get("CreateTime");
				String msgId = params.get("MsgId");
				msgInfo.setCreateTime((createTime!=null&&!"".equals(createTime))?Long.parseLong(createTime):0);
				msgInfo.setFromUserName(params.get("FromUserName"));
				msgInfo.setMsgId((msgId!=null&&!"".equals(msgId))?Long.parseLong(msgId):0);
				msgInfo.setToUserName(params.get("ToUserName"));
				BaseXmlHandler handler = new BaseXmlHandlerImpl();
				BaseMessage msg =MsgDispose(msgInfo,params,handler);
				result = handler.toMsgXml(msg);
			}else{
				result="params format is error!";
			}
		}
		return result;
	}
	
	/**
	 * 默认处理的核心方法
	 * @param msg	消息基类
	 * @param params	通过dom4j读取出来的数据;
	 * @return
	 */
	public BaseMessage MsgDispose(BaseMessageUserInfo msg, Map<String, String> params,BaseXmlHandler handler) {
		BaseMessage result=null;
		String msgType = params.get("MsgType");
		if (msgType != null && !"".equals(msgType)) {
			switch (msgType) {
			case REQ_MESSAGE_TYPE_TEXT: // 文本消息
				result = textMsg(msg, params);
				break;
			case REQ_MESSAGE_TYPE_IMAGE: // 图片消息
				result = imageMsg(msg, params);
				break;
			case REQ_MESSAGE_TYPE_LINK: // 链接消息
				result = linkMsg(msg, params);
				break;
			case REQ_MESSAGE_TYPE_LOCATION: // 地理位置
				result = locationMsg(msg, params);
				break;
			case REQ_MESSAGE_TYPE_VOICE: // 音频消息
				result = shortvideo(msg, params);
				break;
			case REQ_MESSAGE_TYPE_SHORTVIDEO: // 短视频消息
				result = shortvideo(msg, params);
				break;
			case REQ_MESSAGE_TYPE_VIDEO: // 音频消息
				result = videoMsg(msg, params);
				break;
			case REQ_MESSAGE_TYPE_EVENT: // 事件消息
				String eventType = params.get("Event"); //
				if (eventType != null && !"".equals(eventType)) {
					switch (eventType) {
					case EVENT_TYPE_SUBSCRIBE:
						result=subscribe(msg, params);
						break;
					case EVENT_TYPE_UNSUBSCRIBE:
						result=unsubscribe(msg, params);
						break;
					case EVENT_TYPE_SCAN:
						result=unsubscribe(msg, params);
						break;
					case EVENT_TYPE_LOCATION:
						result=unsubscribe(msg, params);
						break;
					case EVENT_TYPE_CLICK:
						result=eventClick(msg, params);
						break;
					case EVENT_TYPE_VIEW:
						result=eventClick(msg, params);
						break;
					default:
						eventDefaultReply(msg, params);
						break;
					}
				} else {
					eventTypeIsNullDefaultReply(msg, params);
				}
				break;
			default:
				defaultMsg(msg, params);
			}
		} else {
			msgTypeIsNullDefaultReply(msg, params);
		}
		return result;
	}
}
