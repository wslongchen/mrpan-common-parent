package com.mrpan.wechat.bean.results.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取属性文件
 */
public class PropertiesUtils {
	
	/**
	 * 根据key值获取对应的value值
	 * @param key	key值
	 * @return
	 */
	public String getPropertiesValue(String key){
		String result = "";
		if(key!=null&&!"".equals(key)){
			InputStream input = this.getClass().getClassLoader().getResourceAsStream("info.properties");
			Properties properties = new Properties();
			try {
				properties.load(input);
				result = properties.getProperty(key,"");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
