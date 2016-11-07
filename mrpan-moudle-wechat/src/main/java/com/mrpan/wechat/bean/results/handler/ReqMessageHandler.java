package com.mrpan.wechat.bean.results.handler;

import com.mrpan.wechat.bean.req.BaseMessage;
import com.mrpan.wechat.bean.req.BaseMessageUserInfo;

import java.util.Map;


/**
 * 请求消息处理
 */
public interface ReqMessageHandler {
	
	/**
	 * 用户发送的为文本消
	 * @param msg		基础消息
	 * @param params	请求参数 
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage textMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	
	/**
	 * 连接消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage linkMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 默认执行的消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage defaultMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 音乐执行的消息
	 * @param msg	基础参数
	 * @param params	请求参数
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage musicMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 图片消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage imageMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 地理位置消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage locationMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 语言消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage voiceMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 视频消息
	 * @param msg 消息基类
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage videoMsg(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 小视频消息
	 * @param msg
	 * @param params
	 * @return  返回需要该消息回复的xml格式类型的字符串
	 */
	public BaseMessage shortvideo(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 消息类型为空时的默认回复,(该方法可不实现 情况基本没有)
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage msgTypeIsNullDefaultReply(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 事件类型为空时的默认回复 	(MsgType为event Event==null的情况)
	 * (该方法可不实现,)
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage eventTypeIsNullDefaultReply(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 用户关注时调用的方法
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage subscribe(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 取消关注时调用的方法
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage unsubscribe(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 用户已关注时的事件推送
	 * @param msg	
	 * @param params
	 * @return
	 */
	public BaseMessage scan(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 上报地理位置事件
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage eventLocation(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 点击菜单拉取消息时的事件推送 (自定义菜单的click在这里做响应)
	 * @param msg  
	 * @param params
	 * @return
	 */
	public BaseMessage eventClick(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 点击菜单跳转链接时的事件推送 (自定义菜单的view在这里做响应)
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage eventView(BaseMessageUserInfo msg, Map<String, String> params);
	
	/**
	 * 事件类型默认返回
	 * @param msg
	 * @param params
	 * @return
	 */
	public BaseMessage eventDefaultReply(BaseMessageUserInfo msg, Map<String, String> params);
	
	
}
