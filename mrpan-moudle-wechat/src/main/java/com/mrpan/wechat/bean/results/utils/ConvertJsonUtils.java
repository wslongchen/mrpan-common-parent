package com.mrpan.wechat.bean.results.utils;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mrpan.wechat.bean.results.WechatResult;

/**
 * 转换json工具类
 */
public class ConvertJsonUtils {
	
	/**
	 * 将java对象转换为json格式的字符串
	 * @param object  带转换的对象
	 * @return	json格式的字符串
	 */
	public static String toJsonString(Object object){
		return JSONObject.toJSONString(object);
	}
	
	/**
	 * 默认日期
	 * @param object
	 * @return
	 */
	public static String toJsonWithDefaultDateFormatString(Object object){
		return toJsonWithDateString(object,"yyyy-MM-DD hh:mm:ss");
	}
	
	/**
	 * 带有日期格式的json格式字符串转换
	 * @param object
	 * @param format
	 * @return
	 */
	public static String toJsonWithDateString(Object object,String format){
		return JSONObject.toJSONStringWithDateFormat(object,format, SerializerFeature.PrettyFormat);
	}
	
	/**
	 * 将对象转换为json格式字符串(包括子类的,与toJsonString具体区别可以参考自定义菜单)
	 * @param object
	 * @return
	 */
	public static String toJSONAndChildStr(Object object){
		return JSONObject.toJSON(object).toString();
	}
	
	/**
	 * 将json格式的数据转换为java对象(不包括数组,集合类型)
	 * @param <T>		
	 * @param jsonData	json格式的字符串
	 * @param t			待转换成的java对象
	 * @return
	 */
	public static <T> T jsonToJavaObject(String jsonData,Class<T> t){
		return JSONObject.parseObject(jsonData, t);
	}
	
	/**
	 * 将json格式的数据转换为java集合对象 ,List集合,map集合
	 * @param jsonData	待转换的json格式的字符串
	 * @param t			泛型集合对象
	 * @return
	 */
	public static <T> List<T> jsonToJavaList(String jsonData,Class<T> t){
		return JSONObject.parseArray(jsonData, t);
	}
	
	/**
	 * 将结果集中判断是否有关键字,如果有关键字,则返回转换后的对象,否则返回原json字符串
	 * @param jsonData	待转换的json格式字符串
	 * @param t			待转换的类型
	 * @param keyword	判断关键字
	 * @return			Result.success==true Result.Obj==传入的类型对象,否则返回原json格式的字符串
	 */
	public static <T> WechatResult ConvertJavaObjectByKeyword(String jsonData, Class<T> t, String keyword){
		WechatResult result = new WechatResult();
		JSONObject json = JSONObject.parseObject(jsonData);
		if(json.containsKey(keyword)){
			result.setObj(JSONObject.parseObject(jsonData, t));
			result.setSuccess(true);
		}else{
			result.setObj(jsonData);
		}
		return result;
	}

	/**
	 * 判断json格式中数据是否包含某个关键字
	 * @param jsonData	待判断的json格式字符串
	 * @param keyword	关键字			注意传入的关键字必须是根目录下的key值
	 * @return	true 表示存在,false 表示不存在
	 */
	public static boolean jsonDataHasKeyWord(String jsonData,String keyword){
		boolean result = false;
		if(jsonData!=null&&!"".equals(jsonData)){
			JSONObject object = JSONObject.parseObject(jsonData);
			if(object.containsKey(keyword)){
				result = true;
			}
		}
		return result;
	}
}
