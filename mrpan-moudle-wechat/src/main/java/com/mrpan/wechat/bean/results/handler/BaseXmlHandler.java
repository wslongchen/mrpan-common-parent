package com.mrpan.wechat.bean.results.handler;


import com.mrpan.wechat.bean.req.BaseMessage;

/**
 * xml 处理类
 * @author Andy
 */
public interface BaseXmlHandler{
	
	/**
	 * 将java对象转换为xml
	 * @param msg 待转换的对象
	 * @return  已经转换好的xml格式字符
	 */
	public String toMsgXml(BaseMessage msg);
}
