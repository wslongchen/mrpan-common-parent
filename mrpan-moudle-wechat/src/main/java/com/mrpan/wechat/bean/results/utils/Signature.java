package com.mrpan.wechat.bean.results.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import com.mrpan.wechat.pay.annotation.NotSign;
import com.mrpan.wechat.utls.Configure;
import com.mrpan.wechat.utls.XMLParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * 获取签名
 */
public class Signature {
	private static Logger log = Logger.getLogger(Signature.class);
    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @param Apikey API密匙
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getNotSign(Object o,String Apikey){
        ArrayList<String> list = new ArrayList<String>();
        String result="";
        try {
			Class cls = o.getClass();
			Field[] fields = cls.getDeclaredFields();
			list = getFieldList(fields, o);
			Field[] superFields = cls.getSuperclass().getDeclaredFields(); //获取父类的私有属性
			list.addAll(getFieldList(superFields, o));
			int size = list.size();
			String[] arrayToSort = list.toArray(new String[size]);
			Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(arrayToSort[i]);
			}
			result = sb.toString();
			 if(Apikey!=null&&!"".equals(Apikey)){
				 result += "key="+Apikey;
			 }else{
				 result = result.substring(0,result.lastIndexOf("&"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    public static String getSign(Object o,String Apikey){
    	String result = getNotSign(o,Apikey);
    	result = EncryptUtils.EncryptMD5(result).toUpperCase();
		return result;
    }
        
    /**
     * 将字段集合方法转换为String	   过滤掉不参与签名的字段
     * @param array	
     * @param object
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static ArrayList<String> getFieldList(Field[] array,Object object) throws IllegalArgumentException, IllegalAccessException{
    	ArrayList<String> list = new ArrayList<String>();
    	 for(Field f:array){
      	   f.setAccessible(true);
      	   if(f.isAnnotationPresent(NotSign.class)){
        	   if(!f.getAnnotation(NotSign.class).value()){
        		   if (f.get(object) != null && f.get(object) != "") {
	                   list.add(f.getName() + "=" + f.get(object) + "&");
	               }
        	   }
      	   }else{
      		   if (f.get(object) != null && f.get(object) != "") {
                   list.add(f.getName() + "=" + f.get(object) + "&");
               } 
      	   }
    	 }
    	 return list;
    }

    /**
     * 通过Map<String,Object>中的所有元素参与签名
     * @param map	待参与签名的map集合
     * @params apikey apikey中 如果为空则不参与签名，如果不为空则参与签名
     * @return
     */
    public static String getSign(Map<String,Object> map,String apiKey){
    	String result=NotSignParams(map,apiKey);
        log.info("Sign Before MD5:" + result);
        result = EncryptUtils.EncryptMD5(result).toUpperCase();
        log.info("Sign Result:" + result);
        return result;
    }
    
    /**
     * 返回未加密的字符串
     * @param params
     * @param apiKey
     * @return		待加密的字符串
     */
    public static String NotSignParams(Map<String,Object> params,String apiKey){
    	 ArrayList<String> list = new ArrayList<String>();
         for(Map.Entry<String,Object> entry:params.entrySet()){
             if(entry.getValue()!=""&&entry.getValue()!=null){
                 list.add(entry.getKey() + "=" + entry.getValue() + "&");
             }
         }
         int size = list.size();
         String [] arrayToSort = list.toArray(new String[size]);
         Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
         StringBuilder sb = new StringBuilder();
         for(int i = 0; i < size; i ++) {
             sb.append(arrayToSort[i]);
         }
         String result = sb.toString();
         if(apiKey!=null&&!"".equals(apiKey)){
         	 result += "key=" + Configure.getKey();
         }else{
         	result = result.substring(0,result.lastIndexOf("&"));
         }
         return result;
    }
    /**
     * SHA1Sign 签名
     * @param params
     * @return
     */
    public static String getSHA1Sign(Map<String,Object> params){
    	String result =NotSignParams(params,"");
        result = EncryptUtils.EncryptSHA1(result);
        return result;
    }
    /**
     * 从API返回的XML数据里面重新计算一次签名
     * @param responseString API返回的XML数据
     * @return 新鲜出炉的签名
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static String getSignFromResponseString(String responseString,String apiKey) throws IOException, SAXException, ParserConfigurationException {
        Map<String,Object> map = XMLParser.getMapFromXML(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return Signature.getSign(map,apiKey);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString,String apiKey) throws ParserConfigurationException, IOException, SAXException {

        Map<String,Object> map = XMLParser.getMapFromXML(responseString);

        String signFromAPIResponse = map.get("sign").toString();
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
        	log.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        log.info("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map, apiKey);
        if(!signForAPIResponse.equals(signFromAPIResponse)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
        	log.info("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        log.info("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
}
