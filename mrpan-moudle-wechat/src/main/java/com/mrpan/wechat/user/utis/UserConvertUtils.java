package com.mrpan.wechat.user.utis;

import com.alibaba.fastjson.JSONObject;
import com.mrpan.wechat.bean.results.WechatResult;
import com.mrpan.wechat.bean.results.utils.ConvertJsonUtils;
import com.mrpan.wechat.bean.user.UserOpenListInfo;
import com.mrpan.wechat.pay.user.entity.WeixinUser;
import com.mrpan.wechat.user.request.BatchGet;
import com.mrpan.wechat.user.request.BatchGetRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrpan on 2016/11/5.
 */
public class UserConvertUtils {

    /**
     * (测试成功)
     * 将json格式的字符串转换为List集合
     * @param jsonData	json格式的字符串,请注意该方法为  getUserOpenList返回结果的定制方法,其余否则会报错
     * @return	WechatResult.isuccess==true 表示成功   Object中则为 UserOpenListInfo对象, 否则为为失败信息
     */
    public static WechatResult getOpenIdList(String jsonData){
        WechatResult resultObj = new WechatResult();
        boolean result = ConvertJsonUtils.jsonDataHasKeyWord(jsonData,"data");		//表示获取成功
        if(result){
            UserOpenListInfo info = JSONObject.parseObject(jsonData,UserOpenListInfo.class);
            JSONObject object = JSONObject.parseObject(jsonData);
            String data = object.getJSONObject("data").getString("openid");			//
            List<String> list = ConvertJsonUtils.jsonToJavaList(data,String.class);
            info.setOpenid(list);
            resultObj.setObj(info);
            resultObj.setSuccess(true);
        }
        resultObj.setMsg(jsonData);
        return resultObj;
    }

    /**
     * (测试成功)
     * 根据用户openid列表获取用户信息
     * 该方法待改进,  改进原因: 有多少条数据,则需要创建多少个对象,严重影响效率
     * @param list
     * @return	resultObj.success=true 则obj中获取的是BatchGetRequest对象
     */
    public static WechatResult convertRequestParams(List<String> list, String lang){
        WechatResult resultObj = new WechatResult();
        BatchGetRequest request = null;
        if(list!=null&&list.size()>0){
            request = new BatchGetRequest();
            List<BatchGet> listget = new ArrayList<BatchGet>();
            request.setUser_list(listget);
            for(String item:list){
                BatchGet get = new BatchGet();				//待改进
                get.setOpenid(item);
                get.setLang((lang==null||"".equals(lang))?"zh_CN":lang);				//默认为zh_CN
                listget.add(get);
            }
            resultObj.setObj(request);
            resultObj.setSuccess(true);
        }
        return resultObj;
    }

    /**
     * (测试成功)
     * 根据返回结果集转换为用户详细信息参数
     * @param data
     * @return
     */
    public static WechatResult getListUserInfo(String data){
        WechatResult result = new WechatResult();
        if(data!=null&&!"".equals(data)){
            boolean success = ConvertJsonUtils.jsonDataHasKeyWord(data,"user_info_list");
            if(success){
                String arrayData = JSONObject.parseObject(data).getString("user_info_list");
                List<WeixinUser> listuser = ConvertJsonUtils.jsonToJavaList(arrayData,WeixinUser.class);
                if(listuser.size()>0){
                    result.setSuccess(true);
                    result.setObj(listuser);
                }
            }
        }
        return result;
    }



}
