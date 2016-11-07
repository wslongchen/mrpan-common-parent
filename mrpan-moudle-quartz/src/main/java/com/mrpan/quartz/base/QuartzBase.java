package com.mrpan.quartz.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by mrpan on 2016/11/5.
 */
public class QuartzBase implements Serializable {
    @Autowired
    private Properties propertiesBean;

    protected String url = "";
    protected String username = "";
    protected String password = "";

    private static final Logger logger = LoggerFactory.getLogger(QuartzBase.class);

    public String convertSqlDate(String strDate) {
        String result = "to_date('" + strDate + "' , 'yyyy-mm-dd hh24:mi:ss')";
        return result;
    }

    public Connection getConnection() throws Exception {
        Connection con = null;
        try {
            String url = this.propertiesBean.getProperty("jdbc.url");
            String username = this.propertiesBean.getProperty("jdbc.username");
            String password = this.propertiesBean.getProperty("jdbc.password");
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();// 加载OrFacle驱动程序
            con = DriverManager.getConnection(url, username, password);// 获取连接
        } catch (Exception ex) {
            throw ex;
        }
        return con;
    }

    public String getSystemParam(Connection con, String paramkind) throws Exception {
        Statement stat = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet resultSet = null;// 创建一个结果集对象
        String mobile = "18658768888";
        try {
            String sql = "select * from xxx";
            logger.info("getSystemParam==" + sql);
            stat = con.createStatement();
            resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                mobile = resultSet.getString("paramvalue");
            }
            if (resultSet != null)
                resultSet.close();
            if (stat != null)
                stat.close();
        } catch (Exception ex) {
            logger.info("saverecharge:" + ex.getMessage());
            throw ex;
        }
        return mobile;
    }
}
