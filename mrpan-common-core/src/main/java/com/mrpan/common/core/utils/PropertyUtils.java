package com.mrpan.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.io.*;
import java.util.*;

/**
 * Created by mrpan on 2016/11/8.
 */
public class PropertyUtils implements BeanFactoryAware {

    /**
     * 根据prefix读取符合该前缀的属性值的List集合
     * @param prefix
     * @return
     */
    public List<String> getList(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                list.add(properties.getProperty(key));
            }
        }
        return list;
    }

    /**
     * 根据prefix读取符合该前缀的属性值的Set集合
     * @param prefix
     * @return
     */
    public Set<String> getSet(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptySet();
        }
        Set<String>set=new TreeSet<String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                set.add(properties.getProperty(key));
            }
        }
        return set;
    }


    /**
     * 根据prefix读取符合该前缀的值的Map集合
     * @param prefix
     * @return
     */
    public Map<String, String> getMap(String prefix) {
        if (properties == null || prefix == null) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> en = properties.propertyNames();
        String key;
        int len = prefix.length();
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                map.put(key.substring(len), properties.getProperty(key));
            }
        }
        return map;
    }

    /**
     * 根据prefix读取符合前缀的Properties对象
     * @param prefix
     * @return
     */
    public Properties getProperties(String prefix) {
        Properties props = new Properties();
        if (properties == null || prefix == null) {
            return props;
        }
        Enumeration<?> en = properties.propertyNames();
        String key;
        int len = prefix.length();
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.startsWith(prefix)) {
                props.put(key.substring(len), properties.getProperty(key));
            }
        }
        return props;
    }

    /**
     * 根据prefix读取符合该前缀的属性值
     * @param prefix
     * @return
     */
    public String getPropertiesString(String prefix) {
        String property = "";
        if (properties == null || prefix == null) {
            return property;
        }
        Enumeration<?> en = properties.propertyNames();
        String key;
        while (en.hasMoreElements()) {
            key = (String) en.nextElement();
            if (key.equals(prefix)) {
                return properties.getProperty(key);
            }
        }
        return property;
    }

    public Map<String, Object> getBeanMap(String prefix) {
        Map<String, String> keyMap = getMap(prefix);
        if (keyMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>(keyMap.size());
        String key, value;
        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            resultMap.put(key, beanFactory.getBean(value, Object.class));
        }
        return resultMap;
    }

    public static Properties getProperties(File file) {
        Properties props = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(file);
            props.load(in);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return props;
    }

    public static String getPropertyValue(File file, String key) {
        Properties props=getProperties(file);
        return (String) props.get(key);
    }

    private BeanFactory beanFactory;
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
