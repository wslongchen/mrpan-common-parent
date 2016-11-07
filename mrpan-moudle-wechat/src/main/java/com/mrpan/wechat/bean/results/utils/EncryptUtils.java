package com.mrpan.wechat.bean.results.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 加密工具类
 */
public class EncryptUtils {
	
	/**
	 * MD5加密
	 * @param str	待加密的字符串
	 * @return		加密后的字符串
	 */
	public static String EncryptMD5(String str){
		StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			/// 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
			digest.update(str.getBytes("utf-8"));
			//计算出摘要
			byte array[] = digest.digest();
			int i;
			for (int offset = 0; offset < array.length; offset++) {
				i = array[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buffer.append("0");
				}
				// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
				buffer.append(Integer.toHexString(i));
			}
			//32位加密
			str = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//16位加密
		//str = buffer.toString().md5Strstring(8,24);;
		//计算出
		return str;
	}
	/**
	 * 验证该消息是否来自微信服务器
	 * @param timestamp	   时间戳
	 * @param token	     token,与accessToken不同
	 * @param nonce		  随机签名数字
	 * @params signature   微信返回的签名
	 * @return	  true 表示校验成功       false 表示校验失败
	 */
	public static boolean EncryptSHA1(String timestamp,String token,String nonce,String signature){
		boolean fig = false;
		String[] array = {token,timestamp,nonce};
		if(array!=null&&array.length>0){
			Arrays.sort(array);
		}
		String result = array[0]+array[1]+array[2];
		String digest = new SHA1().getDigestOfString(result.getBytes()).toLowerCase();
		if(digest.equals(signature)){
			fig = true;
		}
		return fig;
	}
	
	/**
	 * 字符串通过SHA1加密
	 * @param result	   待加密的字符串
	 * @return			 加密后的字符串
	 */
	public static String EncryptSHA1(String result){
		String digest = new SHA1().getDigestOfString(result.getBytes()).toLowerCase();
		return digest;
	}
}
