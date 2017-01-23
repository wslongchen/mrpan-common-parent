package com.mrpan.vpnplatform.web.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by mrpan on 2017/1/23.
 */
public class MailUtils {
    private static String hostName="smtp.qq.com";//设置smtp服务器
    private static String sendMailAddress="1007310431@qq.com";//设置发送地址
    private static String mailPassword="ofhxoqffgzerbdei";//设置密码
    private static boolean TLS = true;//设置是否需要TLS登录

    public static  void  send(String titile,String content,String[] mailAddress,String attachmentFilePath){

        try {

            if(StringUtils.isNotBlank(attachmentFilePath)){

                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(attachmentFilePath);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("文件");
                attachment.setName("附件内容");
                HtmlEmail simpleEmail = new HtmlEmail();
                simpleEmail.setHostName(hostName);
                simpleEmail.setAuthentication(sendMailAddress, mailPassword);
                simpleEmail.setSmtpPort(25);
                simpleEmail.setFrom(sendMailAddress, "小安安");
                simpleEmail.setSubject("utf-8");
                simpleEmail.setMsg(content);
                simpleEmail.attach(attachment);
                for(int i = 0; i < mailAddress.length; ++i){
                    simpleEmail.addTo(mailAddress[i]); //接收方
                    simpleEmail.send();
                }
            }else{
                for(int i = 0; i < mailAddress.length; ++i){
                    HtmlEmail  email = new HtmlEmail();
                    email.setTLS(TLS); //是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验
                    email.setHostName(hostName);
                    email.setFrom(sendMailAddress, sendMailAddress);
                    email.setAuthentication(sendMailAddress, mailPassword);
                    email.setCharset("utf-8");//解决中文乱码问题
                    email.setSubject(titile); //标题
                    email.setMsg(content);//内容

                    email.addTo(mailAddress[i]); //接收方
                    email.send();
                }
            }
        } catch (EmailException e) {
            //  e.printStackTrace();
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSendMailAddress() {
        return sendMailAddress;
    }

    public void setSendMailAddress(String sendMailAddress) {
        this.sendMailAddress = sendMailAddress;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public boolean isTLS() {
        return TLS;
    }

    public void setTLS(boolean tls) {
        TLS = tls;
    }

    public static void main(String [] args){
        String html="<h1>这是一封测试邮件</h1><p><table style=\"width:100%;\" cellpadding=\"2\" cellspacing=\"0\" border=\"1\" bordercolor=\"#000000\"><tbody><tr><td>今日报表</td>"+
                "<td><br/></td></tr><tr><td>交易量1&nbsp;&nbsp;&nbsp;&nbsp;</td><td>1</td></tr><tr><td>11</td><td>11</td></tr></tbody></table><br /></p><p>"+
                "<img src=\"http://api.map.baidu.com/staticimage?center=121.473704%2C31.230393&zoom=11&width=558&height=360&markers=121.473704%2C31.230393&markerStyles=l%2CA\" alt=\"\" /><span style=\"line-height:1.5;\">or vilify them. About the only thing you can’t do is ignore them. Because they change things. They push the human race forward. And while some may see them as the crazy ones, we see genius. Because the people who are crazy enough to think they can change the world, are the ones who do.</span>"+
                "</p><p>- Apple Inc.</p>";
        MailUtils.send("开发部",html,new String[]{"1007310431@qq.com"},null);
    }
}
