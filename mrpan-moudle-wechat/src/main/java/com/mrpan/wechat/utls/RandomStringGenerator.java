package com.mrpan.wechat.utls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 随机字符串的生成
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @param length    在该范围内生成随机数
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return BasicMethod(length,base);
    }
    
    /**
     * 纯数字的随机数
     * @param length 获取随机数的数量
     * @return
     */
    public static String getRandomNumber(int length){
    	String base = "0123456789";
    	return BasicMethod(length,base);
    }
    
    /**
     * 生成随机数的基本方法
     * @param length	随机数的长度
     * @param base		随机数的生成范围
     * @return
     */
    private static String BasicMethod(int length,String base){
    	Random random = new Random();
    	StringBuffer buffer = new StringBuffer();
    	for(int i=0;i<length;i++){
    		int number = random.nextInt(base.length());
    		buffer.append(base.charAt(number));
    	}
    	return buffer.toString();
    }
    
    /**
     * 生成订单id,系统时间的毫秒及4位随机数
     * @return
     */
    public static String getOrderRandomId(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String data = format.format(new Date());
		data= data+RandomStringGenerator.getRandomNumber(4);
		return data;
    }
}
