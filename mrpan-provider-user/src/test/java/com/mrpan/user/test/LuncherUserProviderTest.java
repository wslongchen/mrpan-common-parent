package com.mrpan.user.test;

import com.mrpan.user.bean.User;
import com.mrpan.user.service.UserService;
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
            User u=new User();
            u.setName("longchen");
            u.setPassword("123");

            UserService userService = (UserService)context.getBean("userService");
            //userService.addUser(u);
            User user=userService.findUser("longchen",null);
            log.info("sssssssssssssssssssssssss");
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
