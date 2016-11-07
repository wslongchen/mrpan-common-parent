package com.mrpan.quartz.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Date;

/**
 * Created by mrpan on 2016/11/5.
 */
public class CmdLineLauncher {
    private static final Logger logger = LoggerFactory.getLogger(CmdLineLauncher.class);
    private static volatile boolean running = true;

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        try {
            logger.info("Copyright(C)2015–2020 MrPan. All rights reserved. 1049058427@qq.com.");
            logger.info("定时服务系统启动...!({})", new Date());
            // Properties prop = new Properties();
            // InputStream in = Object.class
            // .getResourceAsStream("/jdbc.properties");
            // prop.load(in);
            // String url = prop.getProperty("jdbc.url").trim();
            // String user = prop.getProperty("jdbc.username").trim();
            // String password = prop.getProperty("jdbc.password").trim();

            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");

            String[] names = context.getBeanDefinitionNames();
            System.out.print("Beans:");
            for (String string : names)
                System.out.print(string + ",");
            System.out.println("启动系统");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    // for (Container container : containers) {
                    try {
                        // container.stop();
                        logger.info("	 stopped!");
                    } catch (Throwable t) {
                        logger.error(t.getMessage(), t);
                    }
                    synchronized (CmdLineLauncher.class) {
                        running = false;
                        CmdLineLauncher.class.notify();
                    }
                    // }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        synchronized (CmdLineLauncher.class) {
            while (running) {
                try {
                    CmdLineLauncher.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
}
