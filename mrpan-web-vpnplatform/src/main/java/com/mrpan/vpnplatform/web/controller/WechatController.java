package com.mrpan.vpnplatform.web.controller;

import com.mrpan.common.core.utils.RenderKit;
import com.mrpan.common.core.utils.WebUtil;
import com.mrpan.user.bean.Ann_User;
import com.mrpan.user.bean.Ann_Wechat;
import com.mrpan.user.service.Ann_WechatService;
import com.mrpan.vpnplatform.web.BaseController;
import com.mrpan.vpnplatform.web.WebConstant;
import com.mrpan.wechat.auth.AuthConn;
import com.mrpan.wechat.bean.req.TextMessage;
import com.mrpan.wechat.bean.results.AccessToken;
import com.mrpan.wechat.bean.results.JsonResult;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.bean.results.utils.SHA1;
import com.mrpan.wechat.message.MessageConn;
import com.mrpan.wechat.message.encrypt.AesException;
import com.mrpan.wechat.message.encrypt.MessageEncrpt;
import com.mrpan.wechat.message.encrypt.XMLMessageParse;
import com.mrpan.wechat.message.utils.MessageUtils;
import com.mrpan.wechat.user.UserConn;
import com.mrpan.wechat.utls.Util;
import com.mrpan.wechat.utls.XMLParser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by mrpan on 2017/1/22.
 */
@Controller
@RequestMapping(value="/wechat")
@Scope(value = "prototype")
public class WechatController extends BaseController{
    private static final Logger logger = LoggerFactory
            .getLogger(WechatController.class);
    private UserConn userConn=null;
    private String accessToke="";
    private String openId="";
    private AuthConn authConn=null;
    private MessageConn messageConn=null;
    @Autowired
    private Ann_WechatService ann_WechatService;

    @RequestMapping(value = "/index",method=RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) {

//            RenderKit.renderText(response,ann_wechat.getToken());
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            if(SHA1.checkSignature("weixin",signature,timestamp,nonce)){
                RenderKit.renderText(response,echostr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }

    private void checkToken(Ann_Wechat ann_wechat){

        if(!StringUtils.isNotBlank(accessToke)){
            accessToke=ann_wechat.getToken();
        }
        if(messageConn==null){
            messageConn=new MessageConn();
        }
        String result= messageConn.getCallBackIp(accessToke);
        if(ConvertJsonUtils.jsonToJavaObject(result,JsonResult.class).getErrcode()!=0){
            accessToke=messageConn.getAccessToken(ann_wechat.getOpenId(),ann_wechat.getSecret());
            this.ann_WechatService.updateAccessToken(accessToke);
        }
    }

    @RequestMapping(value = "/index",method=RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if(messageConn==null)
                messageConn=new MessageConn();
            if(!StringUtils.isNotBlank(accessToke)){
                Ann_Wechat ann_wechat=this.ann_WechatService.getWechatConfig();
                checkToken(ann_wechat);
                String xml=Util.inputStreamToString(request.getInputStream());
                Map<String,Object> map= XMLParser.getMapFromXML(xml);

                String reply=this.doReply(map);
                logger.debug("sssssssssssssssssssssss:"+reply);
                RenderKit.renderXml(response,reply);

            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }

    private String doReply( Map<String,Object> map){
        String respXml="";
        String fromUserName=map.get("FromUserName").toString();
        String toUserName=map.get("ToUserName").toString();
        String msgType=map.get("MsgType").toString();
        logger.debug("wechant POST XML PASE:"+fromUserName+","+toUserName+","+msgType);
        // 回复文本消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime((int) System.currentTimeMillis());
        String respContent="";
        textMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);

        // 文本消息
        if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_TEXT)) {
            String content=map.get("Content").toString();
            if(content.contains("vpn") || content.contains("VPN") ||content.contains("Vpn")){
                respContent="VPN功能开发哥哥正在积极上线～";
            }else{
                respContent = DialogReturn();
            }
        }
        // 图片消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_IMAGE)) {
            respContent = "这张黄图不错～";
        }
        // 语音消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VOICE)) {
            respContent = "小安安暂时还听不懂语音消息哦～";
        }
        // 视频消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VIDEO)) {
            respContent = "您发送的是视频消息！";
        }
        // 视频消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
            respContent = "您发送的是小视频消息！";
        }
        // 地理位置消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LOCATION)) {
            respContent = "小安安也不知道地理位置消息哦～";
        }
        // 链接消息
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LINK)) {
            respContent = "您发送的是链接消息！";
        }
        // 事件推送
        else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) {
            // 事件类型
            String eventType = map.get("Event").toString();
            // 关注
            if (eventType.equals(MessageUtils.EVENT_TYPE_SUBSCRIBE)) {
                respContent = "欢迎大大关注小安安，么么哒～";
            }
            // 取消关注
            else if (eventType.equals(MessageUtils.EVENT_TYPE_UNSUBSCRIBE)) {
                // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
            }
            // 扫描带参数二维码
            else if (eventType.equals(MessageUtils.EVENT_TYPE_SCAN)) {
                // TODO 处理扫描带参数二维码事件
            }
            // 上报地理位置
            else if (eventType.equals(MessageUtils.EVENT_TYPE_LOCATION)) {
                // TODO 处理上报地理位置事件
            }
            // 自定义菜单
            else if (eventType.equals(MessageUtils.EVENT_TYPE_CLICK)) {
                // TODO 处理菜单点击事件
            }
        }
        // 设置文本消息的内容
        textMessage.setContent(respContent);
        // 将文本消息对象转换成xml
        respXml = MessageUtils.messageToXml(textMessage);
        return respXml;
    }

    //无聊的回复
    public static String DialogReturn()
    {
        String reply = "";
        int[] RandNum = new int[10];
        Random r = new Random();
        for (int i = 0; i < 10; i++)
        {
            RandNum[i] = r.nextInt(11);
        }
        for (int mess : RandNum)
        {
            switch (mess)
            {
                case 1: reply = "哎...你是不是喜欢小安安我?";
                    break;
                case 2: reply = "你知道吗，我可以查快递和查身份证还有天气哦...";
                    break;
                case 3: reply = "你知道我老大是谁吗？潘安，对就是墙角那个装B的";
                    break;
                case 4: reply = "我不知道你在说什么，我知道我长得帅，低调！";
                    break;
                case 5: reply = "阿西巴，我主人很帅的哦";
                    break;
                case 6: reply = "你可以回复我主人的名字哦...我不会告诉你有惊喜的";
                    break;
                case 7: reply = "O(∩_∩)O哈哈~，小安安的微信公众号是MrPan哦...";
                    break;
                case 8: reply = "有什么意见可以去我主人的网站提哦！(mrpann.com)";
                    break;
                case 9: reply = "别惹我，我生气起来，自己都打！";
                    break;
                default: reply = "我知道你喜欢小安安...么么哒(你也可以叫我说话的哦。格式：小安安 搞基 潘安 潘安是帅哥！)";
                    break;
            }
        }
        return reply;
    }
}
