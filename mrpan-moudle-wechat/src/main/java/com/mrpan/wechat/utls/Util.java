package com.mrpan.wechat.utls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

import com.thoughtworks.xstream.XStream;


/**
 * 工具类
 */
public class Util {


    /**
     * 通过反射的方式遍历对象的属性和属性值，方便调试
     *
     * @param o 要遍历的对象
     * @throws Exception
     */
    public static void reflect(Object o) throws Exception {
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
        }
    }
    
    /**
     * 将输入流转换字节数组
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] readInput(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    /**
     * 将输入流转换为字符串
     * @param is	待转换为字符串的输入流
     * @return		由输入流转换String的字符串
     * @throws IOException
     */
    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }
    
    /**
     * 将字符串转换为输入流
     * @param sInputString 待转换为输入流的字符串
     * @return
     */
    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }
    /**
     * 将xml数据映射到java对象中
     * @param xml	待转换的xml格式的数据
     * @param tClass	待转换为的java对象
     * @return
     */
    public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }

    /**
     * 从Map<String,String>中获取指定类型为String的键
     * @param map		Map<String,String>的键
     * @param key		待获取的键
     * @param defaultValue	默认值
     * @return
     */
    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if (key == "" || key == null) {
            return defaultValue;
        }
        String result = (String) map.get(key);
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }
    
    /**
     * 从Map<String,String>中获取类型为Int的键
     * @param map	
     * @param key	待获取的键
     * @return
     */
    public static int getIntFromMap(Map<String, Object> map, String key) {
        if (key == "" || key == null) {
            return 0;
        }
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt((String) map.get(key));
    }

    /**
     * 读取本地的xml数据，一般用来自测用
     * @param localPath 本地xml文件路径
     * @return 读到的xml字符串
     */
    public static String getLocalXMLString(String localPath) throws IOException {
        return Util.inputStreamToString(Util.class.getResourceAsStream(localPath));
    }
    
    /**
     * 内容
     * @param content
     */
    public static void log(String content){
    	System.out.println(content);
    }

}

