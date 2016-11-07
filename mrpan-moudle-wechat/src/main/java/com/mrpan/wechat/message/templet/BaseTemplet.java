package com.mrpan.wechat.message.templet;

import java.util.TreeMap;

/**
 * 模板基础选项
 */
public class BaseTemplet{
	
	/**
	 * 获取参数
	 * @param color		文字的颜色
	 * @param value		文字的值
	 * @return  获取已经封装好的 TreeMap 集合
	 */
	public static TreeMap<String,String> ThempleItem(String value,String color){
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("value", value);	
		params.put("color", color);
		return params;
	}
}
