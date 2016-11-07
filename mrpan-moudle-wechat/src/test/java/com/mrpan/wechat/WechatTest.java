package com.mrpan.wechat;

import com.mrpan.wechat.auth.AuthConn;
import com.mrpan.wechat.bean.results.AccessToken;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.user.UserConn;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mrpan on 2016/11/5.
 */
public class WechatTest {

    private UserConn userConn;
    private String accessToke="";
    private String openId="";
    private AuthConn authConn;

    //accessToken=lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI

    @Before
    public void initConfig(){
        userConn=new UserConn();
        authConn=new AuthConn();
    }

    @Test
    public void testAuthConn(){
        try{
            String json="{\"access_token\":\"lRMYV5ffPhTBhZW_W37mPOf7PS9CrY3xKY0w_gGx3FfPFgl1IXW6KBMwUgkUjRDIt_APXno5zwsnAHMkkmf4WIXgUwJRIeIv6Ml-Mb8ECZCchoWNI7-STUPzrSdjKj5mPODiABAUTI\",\"expires_in\":7200}";

            //String token=authConn.getAccessToken("wx8feb13b584234bdd","19138dcca574cd528c398feb6e6164c5");
            System.out.println("token is:"+ConvertJsonUtils.jsonToJavaObject(json, AccessToken.class).getAccess_token());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
