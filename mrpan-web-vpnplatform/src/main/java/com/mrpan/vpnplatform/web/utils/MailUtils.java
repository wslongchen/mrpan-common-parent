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
                simpleEmail.setSmtpPort(465);
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
                    email.setFrom(sendMailAddress, "小安安（MrPan）");
                    email.setAuthentication(sendMailAddress, mailPassword);
                    email.setCharset("utf-8");//解决中文乱码问题
                    email.setSubject(titile); //标题
                    email.setMsg(content);//内容

                    email.addTo(mailAddress[i]); //接收方
                    email.send();
                }
            }
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static  void  send(final String titile, final String content, final String mailAddress, final String attachmentFilePath){

        new Thread(new Runnable() {
            public void run() {
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
                        simpleEmail.setSmtpPort(465);
                        simpleEmail.setFrom(sendMailAddress, "小安安");
                        simpleEmail.setSubject("utf-8");
                        simpleEmail.setMsg(content);
                        simpleEmail.attach(attachment);
                        simpleEmail.addTo(mailAddress); //接收方
                        simpleEmail.send();
                    }else{
                        HtmlEmail  email = new HtmlEmail();
                        email.setTLS(TLS); //是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验
                        email.setHostName(hostName);
                        email.setFrom(sendMailAddress, "小安安（MrPan）");
                        email.setAuthentication(sendMailAddress, mailPassword);
                        email.setCharset("utf-8");//解决中文乱码问题
                        email.setSubject(titile); //标题
                        email.setSocketConnectionTimeout(600000);
                        email.setSocketTimeout(600000);
                        email.setMsg(content);//内容

                        email.addTo(mailAddress); //接收方
                        email.send();
                    }
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            }
        }).run();
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
        StringBuilder builder=new StringBuilder();
        builder.append("<div class=\"rich_media_content \" id=\"js_content\">\n" +
                "                        <p><img src=\"http://mmbiz.qpic.cn/mmbiz_jpg/FyjDNpEQ9licmzYHQKaQJ2F8HayZYatJ9IY5cCEggUY4PwgVuw7yxmMvpW5PYRyFfPoz6nVic2CshTFD6RHpElRQ/0?wx_fmt=jpeg\" style=\"line-height: 1.6; width: 100%; height: auto;\" data-ratio=\"0.6671875\" data-w=\"1280\"  /></p><p><strong>一枚有态度的码农。</strong></p><p><strong><br  /></strong></p><p><strong><br  /></strong></p><p><br  /></p><blockquote><p style=\"text-align: center;\">我想发几条微博记录着自己的生活</p><p style=\"text-align: center;\">总是一个人过着</p><p style=\"text-align: center;\">没事就听一听安静的歌</p><p style=\"text-align: center;\">在每一个孤独的夜晚</p><p style=\"text-align: center;\">总是喝多了酒</p><p style=\"text-align: center;\">有时候也会想起远方的老朋友</p><p style=\"text-align: center;\">也经常怀疑到底什么样的爱情能永垂不朽</p><p style=\"text-align: center;\">我不想同大部分的男人一样过着平庸的生活</p><p style=\"text-align: center;\">不会在碌碌无为中度过</p><p style=\"text-align: center;\">一个人穿过拥挤的人流</p><p style=\"text-align: center;\">错过了就别回头</p><p style=\"text-align: right;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<em>贰佰</em><br  /></p></blockquote><p><br  /></p><p><br  /></p><p><br  /></p><p><br  /></p><p>Tips：本公众号暂时提供VPN以及其他娱乐性服务，详情可戳小安安公众号。</p><p><br  /></p><p><strong>联系方式</strong>：</p><p>&nbsp; &nbsp; <strong>email</strong>：1049058427@qq.com</p><p>&nbsp;&nbsp;&nbsp;&nbsp;<strong>微博</strong>：拯救世界的小安安<br  /></p><p>&nbsp;&nbsp;&nbsp;&nbsp;<strong>微信</strong>：wslongchen<br  /></p><p><br  /></p>\n" +
                "                    </div>");
        MailUtils.send("小安安",builder.toString(),"1049058427@qq.com",null);
    }
}
