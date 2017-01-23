package com.mrpan.wechat;

import com.mrpan.wechat.auth.AuthConn;
import com.mrpan.wechat.bean.menu.Menu;
import com.mrpan.wechat.bean.req.TextMessage;
import com.mrpan.wechat.bean.results.AccessToken;
import com.mrpan.wechat.bean.results.JsonResult;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.menu.MenuConn;
import com.mrpan.wechat.message.MessageConn;
import com.mrpan.wechat.message.utils.MessageUtils;
import com.mrpan.wechat.user.UserConn;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.annotation.Scope;

/**
 * Created by mrpan on 2016/11/5.
 */
public class WechatTest {

    private UserConn userConn;
    private String accessToken="3JusXYTraoOf81Re1fwGsL7iKHsRO452sqL36zzIIzWSzEn7D0AlqOLOGrCYrdGmDMBCUjqhcyp3rInwcIqwRIfI8hFqeyWBdGoEGK-MGLn64utbRYz5nj8H5Eh4KSL_XUMfAAATQI";
    private String openId="";
    private AuthConn authConn;
    private MenuConn menuConn;
    private MessageConn messageConn;

    //accessToken=lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI

    @Before
    public void initConfig(){
        userConn=new UserConn();
        authConn=new AuthConn();
        menuConn=new MenuConn();
        messageConn=new MessageConn();
    }

    @Test
    public void testAuthConn(){
        try{
            String json="{\"access_token\":\"lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI\",\"expires_in\":7200}";

           String token=authConn.getAccessToken("wx8feb13b584234bdd","19138dcca574cd528c398feb6e6164c5");
            System.out.println("token is:"+ConvertJsonUtils.jsonToJavaObject(token, AccessToken.class).getAccess_token());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testMakeMenu(){
        String result= messageConn.getCallBackIp(accessToken);
        if(ConvertJsonUtils.jsonDataHasKeyWord(result,"access_token")){
            System.out.println(result);
        }else{
            System.out.println(ConvertJsonUtils.jsonToJavaObject(result,JsonResult.class).getErrcode()+","+result);
        }

    }

    @Test
    public void testMessege(){
        TextMessage textMessage=new TextMessage();
        textMessage.setContent("sss");
        textMessage.setFromUserName("sddd");
        textMessage.setMsgType("text");
       String ss= MessageUtils.messageToXml(textMessage);
        System.out.println(ss);
    }

}
