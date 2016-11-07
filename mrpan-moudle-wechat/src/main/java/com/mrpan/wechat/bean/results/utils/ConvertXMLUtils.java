package com.mrpan.wechat.bean.results.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mrpan.wechat.bean.resp.BaseMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

/**
 * 转换工具类
 */
public class ConvertXMLUtils {
	public static final String CHARSET_UTF8="utf-8";			//utf-8编码
	public static final String CHARSET_GBK="gbk";				//gbk编码

	/**
	 * 将输入流转换为map集合
	 * @param input	 内容为xml 格式的输入流,否则会出现未知异常
	 * 参数示例值:
	 *  <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName> 
		 <CreateTime>1348831860</CreateTime>
		 <MsgType><![CDATA[text]]></MsgType>
		 <Content><![CDATA[this is a test]]></Content>
		 <MsgId>1234567890123456</MsgId>
		 </xml>
	 * @return 返回示例值: 
	 * {MsgId=1234567890123456, FromUserName=fromUser, CreateTime=1348831860, 
	 * Content=this is a test, ToUserName=toUser, MsgType=text}
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String, String> parseXml(InputStream input) throws DocumentException, IOException{
		// 将解析结果存储在HashMap中  
	    Map<String, String> map = null;
		if(input!=null){
			map = new HashMap<String,String>();
		    // 读取输入流  
		    SAXReader reader = new SAXReader();  
		    Document document = reader.read(input);
		    // 得到xml根元素  
		    Element root = document.getRootElement();  
		    // 得到根元素的所有子节点  
		    List<Element> elementList = root.elements();  
		  
		    // 遍历所有子节点  
		    for (Element e : elementList)  
		        map.put(e.getName(), e.getText());  
		  
		    // 释放资源  
		    input.close();  
		    input = null;  
		}
	    return map;  
	}
	
	/**
	 * 将java对象解析为对应xml格式的字符串
	 * @param msg  待转换的对象,不包括 数组，集合, 仅限于类对象
	 * @return	 xml 格式的字符串
	 */
	public static  String toXMLString(BaseMessage msg){
		String result = "";
		if(msg!=null){
			XStream xs = XStreamFactory.init(true);
			xs.alias("xml",msg.getClass());
			result = xs.toXML(msg);
		}
		return result;
	}
	
	/**
	 * 将输入流转换为字符串
	 * @param input	 	输入流
	 * @return
	 */
	private static String InputStreamToStr(InputStream input,String charset){
		String result = "";
		int len = 0;
		byte[] array = new byte[1024];
		StringBuffer buffer = new StringBuffer();
		if(input!=null){
			try {
				while((len=input.read(array))!=-1){
					buffer.append(new String(array,0,len,charset));
				}
				result = buffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 将输入流转换为utf-8码的字符串
	 * @param input
	 * @return
	 */
	public static String InputStreamToUTF8(InputStream input){
		return InputStreamToStr(input,CHARSET_UTF8);
	}
	
	/**
	 * 将输入流转换为gbk码的字符串
	 * @param input
	 * @return
	 */
	public static String InputStreamToGBK(InputStream input){
		return InputStreamToStr(input,CHARSET_GBK);
	}
	
	/**
	 * 将xml格式的数据转换为输入
	 * @param xmlData	xml格式的数据
	 * @param t			待转换的类
	 * @return			如果如果该输入流中不是xml格式的数据,则会返回null, 使用时一定要判断是否为空g
	 */
	public static <T> Object XmlStrToJavaObject(String xmlData,Class<T> t){
		Object object = null;
		XStream xstream = XStreamFactory.init(true);
		xstream.alias("xml",t);
		try{
			object = xstream.fromXML(xmlData,t);
		}catch(Exception ex){
			object = null;
		}
		return object;
	}
	
	/**
	 * 将内容为xml格式输入流转换为    java对象
	 * @param input				输入流
	 * @param t
	 * @param charset
	 * @return
	 */
	public static <T> Object InputStreamToJavaObject(InputStream input,Class<T> t,String charset){
		Object object = null;
		if(input!=null){
			String result = InputStreamToStr(input,charset);
			if(result!=null&&!"".equals(result)){
				object = XmlStrToJavaObject(result,t);
			}
		}
		return object;
	}
	
}
