package com.mrpan.wechat.pay.handler;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.mrpan.wechat.bean.results.utils.XStreamFactory;
import org.xml.sax.SAXException;

import com.mrpan.wechat.pay.request.RedBackRequest;
import com.mrpan.wechat.pay.request.UnifiedOrderRequest;
import com.mrpan.wechat.pay.response.RedPackResponse;
import com.mrpan.wechat.pay.response.UnifiedOrderResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 支付接口中的转换处理类
 */
public class PayHandler {

	/**
	 * 微信统一支付处理接口
	 * 
	 * @param request
	 * @return
	 */
	public static String unifiedorder(UnifiedOrderRequest request) {
		String result = "";
		if (request != null) {
			XStream xStreamForRequestPostData = new XStream(new DomDriver(
					"UTF-8", new XmlFriendlyNameCoder("-_", "_")));
			xStreamForRequestPostData.alias("xml", UnifiedOrderRequest.class);
			result = xStreamForRequestPostData.toXML(request);
		}
		return result;
	}

	public static String redpack(RedBackRequest request) {
		String resultString="";
		if(null!=request){
			XStream xStream=new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
			xStream.alias("xml", RedBackRequest.class);
			resultString=xStream.toXML(request);
		}
		return resultString;
	}
	
	/**
	 * xml格式的字符串
	 * 
	 * @param xmlResult
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static UnifiedOrderResponse unifiedOrderResponse(
			String xmlResult) {
		/*XStream xstream = new XStream(new DomDriver("UTF-8",
				new XmlFriendlyNameCoder("-_", "_")));*/
		XStream xstream = XStreamFactory.init(true);
		xstream.alias("xml", UnifiedOrderResponse.class);
		UnifiedOrderResponse orderResult = null;
		try {
			orderResult = (UnifiedOrderResponse) xstream.fromXML(xmlResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return orderResult;
	}
	
	/**
	 * @param xmlResult
	 * @return
	 */
	public static RedPackResponse redpackResponse(
			String xmlResult) {
		XStream xstream = XStreamFactory.init(true);
		xstream.alias("xml", RedPackResponse.class);
		RedPackResponse redPackResponse = null;
		try {
			redPackResponse = (RedPackResponse) xstream.fromXML(xmlResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return redPackResponse;
	}
}