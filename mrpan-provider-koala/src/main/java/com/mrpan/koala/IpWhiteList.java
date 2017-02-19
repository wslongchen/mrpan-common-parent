package com.mrpan.koala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by mrpan on 2016/11/3.
 */
@Component("ipWhiteList")
public class IpWhiteList {
    private static final Logger logger = LoggerFactory
            .getLogger(IpWhiteList.class);
    private List<String> whitelist = new ArrayList<String>();

    public List<String> getAllowedIps() {
        return this.whitelist;
    }

    @PostConstruct
    void init() {
        try {
            Properties p = new Properties();
            // fix 启动报错 ipwhitelist.properties 加载不到
            InputStream in = getClass().getClassLoader().getResourceAsStream("ipwhitelist.properties");
			/*
			File file = new File("");
			String msgServerHome = file.getAbsolutePath();
			if (msgServerHome.indexOf(":") == -1) {
				msgServerHome = "/" + msgServerHome;
			}
			InputStream in = new FileInputStream(new File(msgServerHome
					+ "/ipwhitelist.properties"));
			*/
            p.load(in);
            in.close();
            Enumeration<?> enums = p.propertyNames();
            while (enums.hasMoreElements()) {
                String key = (String) enums.nextElement();
                logger.info("Ip:{}", key);
                System.out.println("-------------------------" + key);
                this.whitelist.add(key.trim());
            }

        } catch (Exception ex) {
            logger.error("init:{}", ex.getMessage());
        }
    }
}
