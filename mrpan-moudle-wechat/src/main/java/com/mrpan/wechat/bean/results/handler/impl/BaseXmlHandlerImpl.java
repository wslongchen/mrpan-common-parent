package com.mrpan.wechat.bean.results.handler.impl;

import com.mrpan.wechat.bean.req.BaseMessage;
import com.mrpan.wechat.bean.results.handler.BaseXmlHandler;
import com.mrpan.wechat.bean.results.utils.XStreamFactory;
import com.thoughtworks.xstream.XStream;


/**
 * 处理类
 */
public class BaseXmlHandlerImpl implements BaseXmlHandler {

	/**
	 * 
	 */
	public String toMsgXml(BaseMessage msg) {
		String result = "";
		if(msg!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml",msg.getClass());
			result = xs.toXML(msg);
		}
		return result;
	}
}
