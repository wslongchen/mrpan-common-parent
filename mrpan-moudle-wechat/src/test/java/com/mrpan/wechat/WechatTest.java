package com.mrpan.wechat;

import com.mrpan.wechat.auth.AuthConn;
import com.mrpan.wechat.bean.material.MaterMsg;
import com.mrpan.wechat.bean.material.NewsList;
import com.mrpan.wechat.bean.material.OtherList;
import com.mrpan.wechat.bean.menu.Menu;
import com.mrpan.wechat.bean.req.TextMessage;
import com.mrpan.wechat.bean.results.AccessToken;
import com.mrpan.wechat.bean.results.JsonResult;
import com.mrpan.wechat.bean.results.WechatResult;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.material.MeaterConn;
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
    private String accessToken="hShO3Etl2QwJvvipFLepZDGK82_A3HqquzTeHjxpbj6CsaxkzWG5l5IeqCv3ldf8-8fTQI8ZCaNb6J1V5h9lEDiFAyOOLKDQlaTjOmLgxj8eAjbZSxdh3JFK1PFh-pkKMRSiAGAHMN";
    private String openId="";
    private AuthConn authConn;
    private MenuConn menuConn;
    private MessageConn messageConn;
    private MeaterConn meaterConn;

    //accessToken=lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI

    @Before
    public void initConfig(){
        userConn=new UserConn();
        authConn=new AuthConn();
        menuConn=new MenuConn();
        messageConn=new MessageConn();
        meaterConn=new MeaterConn();

    }

    @Test
    public void testAuthConn(){
        try{
            String json="{\"access_token\":\"lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI\",\"expires_in\":7200}";

           String token=authConn.getAccessToken("wx8feb13b584234bdd","19138dcca574cd528c398feb6e6164c5");
          // System.out.println("token is:"+ConvertJsonUtils.jsonToJavaObject(token, AccessToken.class).getAccess_token());
            System.out.println(token);

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
        try {
            String resultStr="";
            WechatResult result=meaterConn.getMeaterList("Yb6Mv6747ibONnSrKntM2BGLOJjUBRkuZNaCbk8E9whTA0tsR_BZKKme93HD9MEO0Z6nfDdfqtQI3hHd8MV8alFWzS9P9krELBEV9jTwnE8MEnzs-MQ6ur_gFAx7F6j_ORMbACAJMH","news",0,10);
            if(result.isSuccess()==true&&result.getType()==0){
                MaterMsg msg = (MaterMsg) result.getObj();
                resultStr = msg.toString();
            }else if(result.isSuccess()==true&&result.getType()==1){
                NewsList list = (NewsList) result.getObj();
                resultStr = list.toString();
            }else if(result.isSuccess()==true&&result.getType()==2){
                OtherList otherList = (OtherList) result.getObj();
                resultStr = otherList.toString();
            }else{
                resultStr = result.getObj().toString();
            }
           System.out.println(resultStr);
        }catch (Exception e){

        }
    }

}
