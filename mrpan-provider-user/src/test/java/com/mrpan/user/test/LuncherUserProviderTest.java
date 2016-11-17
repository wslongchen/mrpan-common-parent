package com.mrpan.user.test;

import com.mrpan.common.core.utils.MyMD5Util;
import com.mrpan.user.bean.Ann_Menu;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.service.Ann_UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mrpan on 2016/11/3.
 */
public class LuncherUserProviderTest {
    private static final Log log = LogFactory.getLog(LuncherUserProviderTest.class);
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context-user.xml");
            context.start();
            String[] names = context.getBeanDefinitionNames();
            for (String string : names)
                System.out.print(string + ",");
            Ann_User u=new Ann_User();
            u.setUserName("longchen");
            u.setPassword(MyMD5Util.getEncryptedPwd("123456"));
            u.setAuditStatus("1");
            Ann_UserService userService = (Ann_UserService)context.getBean("ann_UserService");
            userService.addUser(u);
            //User user=userService.findUser("admin",null);
           // log.info("sssssssssssssssssssssssss");
            System.out.println();
        } catch (Exception e) {
            log.error("== LuncherUserProviderTest context start error:",e);
        }
        synchronized (LuncherUserProviderTest.class) {
            while (true) {
                try {
                    LuncherUserProviderTest.class.wait();
                } catch (InterruptedException e) {
                    log.error("== synchronized error:",e);
                }
            }
        }
    }
}
